var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?748d0f67de7c0d86228263d1b0f69569";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
function video_end(){
	$('#video_end_img').show();
}
function video_restart(){
	$('#J_video_player').get(0).play();
	$('#video_end_img').hide();
}