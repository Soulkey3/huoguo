package cn.jsut.huoguo.pojo;

import lombok.Data;

/**
 * 商品实体类
 */
@Data
public class Product {
    //商品Id
    private Integer productId;
    //商品名称
    private String productName;
    //价格
    private Integer productPrice;
    //图片路径
    private String imgAddr;
    //种类
    private String category;
    //商品描述
    private String productDesc;
    //商品积分
    private Integer productPoint;
    //商品优先级
    private Integer productPriority;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(Integer productPoint) {
        this.productPoint = productPoint;
    }

    public Integer getProductPriority() {
        return productPriority;
    }

    public void setProductPriority(Integer productPriority) {
        this.productPriority = productPriority;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", imgAddr='" + imgAddr + '\'' +
                ", category='" + category + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productPoint=" + productPoint +
                ", productPriority=" + productPriority +
                '}';
    }
}
