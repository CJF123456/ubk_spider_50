window.onload=function()//用window的onload事件，窗体加载完毕的时候
{
	$.ajax({
		type : "GET",
		url : "readerShowPoetryByconuts.do",
		success : function(msg) {
			var div = document.getElementById("poetry");
            var table = "";
            table += msg;
            div.innerHTML=table;
		}
	});
};


