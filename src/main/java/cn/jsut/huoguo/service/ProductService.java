package cn.jsut.huoguo.service;

import cn.jsut.huoguo.pojo.Product;

import java.util.List;

/**
 * 商品Service层
 */
public interface ProductService {


  List<Product> findAll();

  Product findById(int productId);

  void addProduct(Product product);

  void deleteProductById(int id);

  void editProduct(Product product);

  List<Product> findProductByCategory(String category);


}
