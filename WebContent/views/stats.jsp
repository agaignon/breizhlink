<jsp:include page="layouts/header.jsp" />

<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
	google.charts.load('current', {'packages':['line']});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
	
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'Date');
	  data.addColumn('number', 'Number of clicks');
	
	  var jsonString = '${jsonString}';
	  var array = eval('(' + jsonString + ')');
	  
	  for (i in array)
	  {
		  data.addRow([array[i]["date"], array[i]["nbClick"]]);
	  }
	
	  var options = {
	    chart: {
	      title: 'Number of times the link has been clicked in a day'
	    },
	    width: 900,
	    height: 500
	  };
	
	  var chart = new google.charts.Line(document.getElementById('linechart_material'));
	
	  chart.draw(data, google.charts.Line.convertOptions(options));
	}
  </script>
</head>

<br>
<div class="twelve columns offset-by-three">
	<div id="linechart_material" style="width: 900px; height: 500px"></div>
</div>

<jsp:include page="layouts/footer.jsp" />