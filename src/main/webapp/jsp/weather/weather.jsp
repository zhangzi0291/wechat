<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=0.7, minimum-scale=0.5, maximum-scale=1.5, user-scalable=yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>未来天气</title>
</head>
<body>

	<div id="container" style="height: 20rem; width: 30rem;"></div>

</body>
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="../jsp/weather/echarts.min.js"></script>
<script type="text/javascript">
	var dom = document.getElementById("container");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	myChart.showLoading();
	$.get('futureweather.json?area=${area}', function(data) {
		myChart.hideLoading();

		myChart.setOption(option = {
			title : {
				text : '未来天气折线图',
				left : 'center'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					animation : false
				},
				formatter : function(params) {
					console.log(params)
					return params[0].name + '<br />高温：' + params[0].value
							+ '℃<br />低温：' + params[1].value + "℃<br/>天气："+params[2].value+"<br/>"
							+"风力："+params[3].value+"  "+params[4].value;
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'category',
				data : data.map(function(item) {
					console.log(item)
					return item.date;
				}),
				splitLine : {
					show : false
				},
				boundaryGap : false
			},
			yAxis : {
				axisLabel : {
					formatter : function(val) {
						return val + "℃";
					}
				},
				splitNumber : 5,
				splitLine : {
					show : false
				}
			},
			series : [ {
				name : 'high',
				type : 'line',
				data : data.map(function(item) {
					return item.highNum;
				}),
				hoverAnimation : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
			}, {
				name : 'low',
				type : 'line',
				data : data.map(function(item) {
					return item.lowNum;
				}),
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
				hoverAnimation : false,
				areaStyle : {
					normal : {
						color : '#ccc'
					}
				},
			},
			 {
				name : 'type',
				type : 'line',
				data : data.map(function(item) {
					return item.type;
				}),
				hoverAnimation : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
			} ,
			{
				name : 'fengxiang',
				type : 'line',
				data : data.map(function(item) {
					return item.fengxiang;
				}),
				hoverAnimation : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
			},
			{
				name : 'fengli',
				type : 'line',
				data : data.map(function(item) {
					return item.fengli;
				}),
				hoverAnimation : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
			} ]
		});
	});
	;
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
</script>
</html>