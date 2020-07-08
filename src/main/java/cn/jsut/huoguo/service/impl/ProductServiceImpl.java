package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.dao.ProductDao;
import cn.jsut.huoguo.pojo.Product;
import cn.jsut.huoguo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有商品
     * @return
     */
    @Override
    @Transactional
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 根据id查询菜品
     * @param productId
     * @return
     */
    @Override
    @Transactional
    public Product findById(int productId) {
        return productDao.findById(productId);
    }

    /**
     * 添加菜品
     * @param product
     */
    @Override
    @Transactional
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    /**
     * 删除菜品
     */
    @Override
    @Transactional
    public void deleteProductById(int id) {
        productDao.deleteProductById(id);
    }

    /**
     * 更新菜品
     * @param product
     */
    @Override
    @Transactional
    public void editProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return productDao.findProductByCategory(category);
    }
}
