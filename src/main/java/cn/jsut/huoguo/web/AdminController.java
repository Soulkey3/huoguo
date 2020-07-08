package cn.jsut.huoguo.web;

import cn.jsut.huoguo.pojo.Carousel;
import cn.jsut.huoguo.pojo.Order;
import cn.jsut.huoguo.pojo.Product;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.*;
import cn.jsut.huoguo.vo.DailyCheckVo;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ProductCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 管理Web层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DailyCheckService dailyCheckService;
    @Autowired
    private CarouselService carouselService;


//********************商品管理**********************

    /**
     * 查询所有商品列表
     *
     * @param model
     * @return
     */
    @GetMapping("/productlist")
    public String findAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productlist";
    }

    /**
     * 根据id查询商品信息
     *
     * @return
     */
    @GetMapping("/findById/{productid}")
    public String findById(@PathVariable("productid") Integer productid, Model model) {
        Product product = productService.findById(productid);
        model.addAttribute("product", product);
        return "productdetail";
    }

    /**
     * 添加菜品
     */
    @RequestMapping("/addproduct")
    public String addProduct(MultipartFile file, Product product) throws IOException {
        System.out.println(product);

        /**
         * 上传图片
         * 图片上传成功后，将图片地址写到数据库
         */
        //保存图片路径
        String filePath = "G:\\JavaProject\\huoguo\\src\\main\\resources\\static\\img";
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFilename = UUID.randomUUID() + originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath, newFilename);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        product.setImgAddr(newFilename);

        System.out.println(product);
        //保存菜品
        productService.addProduct(product);
        return "redirect:/admin/productlist";
    }

    /**
     * 根据id删除商品
     */
    @RequestMapping("/deleteproductbyid")
    public String deleteProductById(int productid) {
        //System.out.println(productid);
        productService.deleteProductById(productid);
        return "redirect:/admin/productlist";
    }

    /**
     * 更新菜品
     */
    @PostMapping("/updateproduct")
    public String updateProduct(Product product) {
        System.out.println(product);

        productService.editProduct(product);
        return "redirect:/admin/productlist";
    }


    //********************用户管理**********************

    /**
     * 查询所有商品列表
     *
     * @param model
     * @return
     */
    @GetMapping("/userlist")
    public String findAllUser(Model model) {
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "adminuserlist";
    }


    /**
     * 根据id查询用户信息
     *
     * @return
     */
    @GetMapping("/findUserById/{userid}")
    public String findUserById(@PathVariable("userid") Integer userid, Model model) {
        User user = userService.findUserById(userid);
        model.addAttribute("user", user);
        return "adminuserdetail";
    }


    //********************订单管理**********************
    @RequestMapping("/orderList")
    public String findOrderList(Model model) {
        List<Order> orderList = orderService.findOrderList();
        model.addAttribute("orders", orderList);
        return "back-orderlist";
    }

    @GetMapping("/findOrderById/{orderid}")
    public String findOrderById(@PathVariable("orderid") String orderid, Model model) {
        Order order = orderService.findOrderById(orderid);
        User user = userService.findUserById(order.getUserId());
        List<OrderDetailVo> orderDetailsById = orderService.findOrderDetailById(orderid);
        int amount = 0;//计算订单总商品
        for (OrderDetailVo orderDetailVo : orderDetailsById) {
            amount += orderDetailVo.getCount();
        }
        model.addAttribute("amount", amount);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        model.addAttribute("ordersDetailById", orderDetailsById);
        return "back-orderdetail";
    }

    //********************消费盘点**********************
    @RequestMapping("/dailycheck")
    public String dailycheck(Model model){

        /**
         * 各分类总销售额
         */
        List<DailyCheckVo> category_amountList = dailyCheckService.findDailyCheck();
         model.addAttribute("food",category_amountList.get(0).getAmount());
         model.addAttribute("meat",category_amountList.get(1).getAmount());
         model.addAttribute("vegatable",category_amountList.get(2).getAmount());
         model.addAttribute("bean",category_amountList.get(3).getAmount());
         model.addAttribute("hotpot",category_amountList.get(4).getAmount());
         model.addAttribute("drink",category_amountList.get(5).getAmount());

         //分类最大销量
        List<ProductCountVo> vegatables = dailyCheckService.findProductCount("蔬菜");
        List<ProductCountVo> foods = dailyCheckService.findProductCount("熟菜");
        List<ProductCountVo> meats = dailyCheckService.findProductCount("肉类");
        List<ProductCountVo> beans = dailyCheckService.findProductCount("豆制品");
        List<ProductCountVo> hotpots = dailyCheckService.findProductCount("锅底");
        List<ProductCountVo> drinks = dailyCheckService.findProductCount("饮料");
        model.addAttribute("vegatables",vegatables);
        model.addAttribute("foods",foods);
        model.addAttribute("meats",meats);
        model.addAttribute("beans",beans);
        model.addAttribute("hotpots",hotpots);
        model.addAttribute("drinks",drinks);

        //总销售额
        int dailyAmount=0;
        dailyAmount=category_amountList.get(0).getAmount()+category_amountList.get(1).getAmount()+
                category_amountList.get(2).getAmount()+category_amountList.get(3).getAmount()+category_amountList.get(4).getAmount()+category_amountList.get(5).getAmount();

        model.addAttribute("amount",dailyAmount);
        return "back-dailycheck";
    }


    //********************更改轮播图**********************
    @RequestMapping("/updateCarousel")
    public String updateCarousel(MultipartFile file1,MultipartFile file2,MultipartFile file3,Model model) throws IOException {
        /**
         * 上传图片
         * 图片上传成功后，将图片地址写到数据库
         */
        //保存图片路径
        String filePath = "G:\\JavaProject\\huoguo\\src\\main\\resources\\static\\img";
        //轮播图1
        //获取原始图片的拓展名
        String originalFilename = file1.getOriginalFilename();
        //新的文件名字
        String newFilename = UUID.randomUUID() + originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath, newFilename);
        //把本地文件上传到封装上传文件位置的全路径
        file1.transferTo(targetFile);

        //轮播图2
        //获取原始图片的拓展名
        String originalFilename2 = file2.getOriginalFilename();
        //新的文件名字
        String newFilename2 = UUID.randomUUID() + originalFilename2;
        //封装上传文件位置的全路径
        File targetFile2 = new File(filePath, newFilename2);
        //把本地文件上传到封装上传文件位置的全路径
        file2.transferTo(targetFile2);

        //轮播图3
        //获取原始图片的拓展名
        String originalFilename3 = file3.getOriginalFilename();
        //新的文件名字
        String newFilename3 = UUID.randomUUID() + originalFilename3;
        //封装上传文件位置的全路径
        File targetFile3 = new File(filePath, newFilename3);
        //把本地文件上传到封装上传文件位置的全路径
        file3.transferTo(targetFile3);

        Carousel carousel=new Carousel();
        carousel.setCarouselId(1);
        carousel.setCarouselImg1(newFilename);
        carousel.setCarouselImg2(newFilename2);
        carousel.setCarouselImg3(newFilename3);
        //更新轮播图
        carouselService.updateCarousel(carousel);
        return "redirect:/toshopadmin";
    }



}
