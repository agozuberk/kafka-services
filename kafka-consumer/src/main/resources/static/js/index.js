$( window ).ready(function() {
  connectToLogLiveStream();
  connectToLogCount();
  chart.render();
});

var istanbulLogCount = [];
var moscowLogCount = [];
var tokyoLogCount = [];
var beijingLogCount = [];
var londonLogCount = [];
var dataLength = 10;


var chart = new CanvasJS.Chart("chartContainer", {
	zoomEnabled: true,
	title: {
		text: "Number Of the Logs"
	},
	axisX: {
		title: "Time (Updates every 30 seconds)",
        valueFormatString: "hh:mm:ss"
	},
	axisY:{
		includeZero: false,
		title: "Log Number"
	}, 
	toolTip: {
		shared: true
	},
	data: [{ 
	  		type: "line",
	  		xValueType: "dateTime",
	  		name: "Istanbul",
	  		dataPoints: istanbulLogCount
		}, { 
	  		type: "line",
	  		xValueType: "dateTime",
	  		name: "Moscow",
	  		dataPoints: moscowLogCount
		}, { 
	  		type: "line",
	  		xValueType: "dateTime",
	  		name: "Tokyo",
	  		dataPoints: tokyoLogCount
		}, { 
	  		type: "line",
	  		xValueType: "dateTime",
	  		name: "Beijing",
	  		dataPoints: beijingLogCount
		}, { 
	  		type: "line",
	  		xValueType: "dateTime",
	  		name: "London",
	  		dataPoints: londonLogCount
		}]
});

function connectToLogLiveStream() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
      stompClient.subscribe('/topic/logs', function (log) {
          addLogToStream(log.body);
       });
  });
};

function connectToLogCount() {
	  var socket = new SockJS('/websocket');
	  stompClient = Stomp.over(socket);
	  stompClient.connect({}, function (frame) {
	      stompClient.subscribe('/topic/logcount', function (log) {
	    	  logCount = JSON.parse(log.body);	    	  
	    	  var time = new Date();
	    	  time.setSeconds(time.getSeconds() + 1);

	    	  istanbulLogCount.push({
                  x: time.getTime(),
                  y: Number(logCount.istanbul)
              });
	    	  
	    	  moscowLogCount.push({
                  x: time.getTime(),
                  y: Number(logCount.moscow)
              });
	    	  
	    	  tokyoLogCount.push({
                  x: time.getTime(),
                  y: Number(logCount.tokyo)
              });
	    	  
	    	  beijingLogCount.push({
                  x: time.getTime(),
                  y: Number(logCount.beijing)
              });
	    	  
	    	  londonLogCount.push({
                  x: time.getTime(),
                  y: Number(logCount.london)
              });
	    	  
	    	  if (istanbulLogCount.length > dataLength) {
	    		  istanbulLogCount.shift();
	    		  moscowLogCount.shift();
	    		  tokyoLogCount.shift();
	    		  beijingLogCount.shift();
	    		  londonLogCount.shift();
	    		}
	    	  chart.render();
	    	  
	       });
	  });
	};


function addLogToStream(message) {
    $("#logStream").append("<tr><td>" + message + "</td></tr>");
};
