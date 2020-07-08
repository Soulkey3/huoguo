package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void findAll() {
        List<Product> productList = productDao.findAll();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Test
    public void findById() {
        Product p = productDao.findById(1);
        System.out.println(p);
    }

    @Test
    public void insertProduct() {
        Product product=new Product();
        product.setProductName("test1");
        product.setProductPrice(20);
        product.setImgAddr("test图片");
        product.setCategory("酒水");
        product.setProductDesc("test的描述");
        product.setProductPoint(2);
        product.setProductPriority(0);
        productDao.addProduct(product);

    }

    @Test
    public void deleteProduct() {
        productDao.deleteProductById(7);

    }

    @Test
    public void updateProduct() {
        Product product=new Product();
        product.setProductId(13);
        product.setProductName("test修改");
        product.setProductPrice(10);
        product.setImgAddr("test修改图片");
        product.setCategory("酒水");
        product.setProductDesc("test修改的描述");
        product.setProductPoint(1);
        product.setProductPriority(10);

        productDao.updateProduct(product);
    }

    @Test
    public void findBycategory() {
        List<Product> list = productDao.findProductByCategory("肉类");
        for (Product product : list) {
            System.out.println(product);
        }

    }

}