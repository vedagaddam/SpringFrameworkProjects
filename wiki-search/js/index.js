$("#searchbar").hide();
function searClick(){
	$("#searchbar").show();
}
$( "#inp" ).keydown(function() {
	var variable = $("#inp").val();
	var url = "https://en.wikipedia.org/w/api.php?action=opensearch&search="+ variable +"&format=json&callback=?";
	$.ajax({
		url: url,
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",
		success: function(json) {
			$("#output").html("");
			for(var i=0;i<json[1].length;i++){
				$("#output").prepend("<div><div class='well'><a href="+json[3][i]+" target='_blank'><h1>" + json[1][i]+ "</h1>" + "<p>" + json[2][i] + "</p></a></div></div>");
			}

		}
	});
});