$(function() {

    //设定轮播图轮换时间为3秒
    $(".swiper-container").swiper({
        autoplay : 3000,
        //用户对轮播图进行操作时，是否自动停止autoplay
        autoplayDisableOnInteraction : false
    });
    //若点击"我的"，则显示侧栏
    $('#me').click(function() {
        $.openPanel('#panel-right-demo');
    });

});
