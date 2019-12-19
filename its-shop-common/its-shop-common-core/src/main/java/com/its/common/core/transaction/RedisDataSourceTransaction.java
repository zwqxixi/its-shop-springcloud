package com.its.common.core.transaction;

import com.its.common.core.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @ClassName: RedisDataSourceTransaction
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/2 19:28
 * @Description: mysql与redis事务的共同使用
 */
@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class RedisDataSourceTransaction{

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PlatformTransactionManager txManager;

    //TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
    //todo 事务的传播特性
    //默认:required  如果没有就新开启一个事务，如果有就加入当前事务
    //requireds_new 如果没有就开启一个事务，如果有就将当前事务挂起(等新加入的B事务执行完毕 A再执行)
    //nested 事务嵌套
    //supports  如果没有就以非事务方式运行，如果有就加入当前事务
    public TransactionStatus begin(){
        //手动开启数据库事务
        TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionDefinition());//声明事务类型
        //开启redis事务
        redisUtils.multi();
        return transactionStatus;
    }
   /**
    * 事务提交
    * */
    public void commit(TransactionStatus transactionStatus) throws Exception{
        if(transactionStatus == null){
            throw new Exception("transactionStatus is null");
        }
        txManager.commit(transactionStatus);//支持Redis事务和数据库事务同时提交
        //redisUtils.exec();
    }
    /**
     * 事务回滚
     */
    public void rollback(TransactionStatus transactionStatus) throws Exception{
        if(transactionStatus == null){
            throw new Exception("transactionStatus is null");
        }
        txManager.rollback(transactionStatus);
        redisUtils.discard();//取消数据库事务
    }
}
