package com.example.taskapp.controllers;

import android.content.Context;

import com.example.taskapp.services.IController;
import com.taskApp.task.db.DBSession;
import com.taskApp.task.db.DaoSession;
import com.taskApp.task.db.ProductDetail;
import com.taskApp.task.db.ProductDetailDao;

import java.util.List;

public class ProductController implements IController<ProductDetail> {
    DaoSession session;

    public ProductController(Context context) {session = new DBSession().getSession(context);
    }
    @Override
    public void save(ProductDetail entity) {
        session.getProductDetailDao().save(entity);
    }

    @Override
    public void delete(ProductDetail entity) {
        session.getProductDetailDao().delete(entity);
    }

    @Override
    public List<ProductDetail> getAll() {
        List<ProductDetail> productDetailList = session.getProductDetailDao()
                .queryBuilder()
                .where(ProductDetailDao.Properties.IsActive.eq(true))
                .list();
        return productDetailList;
    }

    @Override
    public List<ProductDetail> getAllByAsec() {
        List<ProductDetail> productDetailList = session.getProductDetailDao()
                .queryBuilder()
                .where(ProductDetailDao.Properties.IsActive.eq(true))
                .orderAsc(ProductDetailDao.Properties.TotalPrice)
                .list();
        return productDetailList;
    }

    @Override
    public List<ProductDetail> getAllByDesc() {
        List<ProductDetail> productDetailList = session.getProductDetailDao()
                .queryBuilder()
                .where(ProductDetailDao.Properties.IsActive.eq(true))
                .orderDesc(ProductDetailDao.Properties.TotalPrice)
                .list();
        return productDetailList;
    }

    @Override
    public List<ProductDetail> getAllByAsecName() {
        List<ProductDetail> productDetailList = session.getProductDetailDao()
                .queryBuilder()
                .where(ProductDetailDao.Properties.IsActive.eq(true))
                .orderAsc(ProductDetailDao.Properties.ProductName)
                .list();
        return productDetailList;
    }

    @Override
    public List<ProductDetail> getAllByDescName() {
        List<ProductDetail> productDetailList = session.getProductDetailDao()
                .queryBuilder()
                .where(ProductDetailDao.Properties.IsActive.eq(true))
                .orderDesc(ProductDetailDao.Properties.ProductName)
                .list();
        return productDetailList;
    }

    @Override
    public ProductDetail getById(Long id) {
        ProductDetail productDetail = session.getProductDetailDao().loadByRowId(id);
        return productDetail;
    }

    @Override
    public void edit(ProductDetail entity) {
        session.getProductDetailDao().update(entity);
        boolean bool = entity.getIsActive();
        List l = getAll();
    }
}
