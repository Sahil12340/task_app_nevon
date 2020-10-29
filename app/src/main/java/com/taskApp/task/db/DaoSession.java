package com.taskApp.task.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.taskApp.task.db.ProductDetail;

import com.taskApp.task.db.ProductDetailDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig productDetailDaoConfig;

    private final ProductDetailDao productDetailDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        productDetailDaoConfig = daoConfigMap.get(ProductDetailDao.class).clone();
        productDetailDaoConfig.initIdentityScope(type);

        productDetailDao = new ProductDetailDao(productDetailDaoConfig, this);

        registerDao(ProductDetail.class, productDetailDao);
    }
    
    public void clear() {
        productDetailDaoConfig.clearIdentityScope();
    }

    public ProductDetailDao getProductDetailDao() {
        return productDetailDao;
    }

}
