$(function(){
	changeBgImg();
	$(window).on("resize",changeBgImg);
});
function changeBgImg(){
	var winWidth = $(window).width();
	$("#carousel-qihangkt>.carousel-inner>.item").each(function(index,el){
		var div = $(el);
		var bgimg = div.data((winWidth <= 768)?"xs-img":"lg-img");
		if(winWidth <= 768){
			div.html('<img src="'+bgimg+'" alt="carousel">');
		}else{
			div.html("");
			div.css('background',"url('"+bgimg+"') no-repeat");
		}
	});

}
