$(document).ready(function() {

  /** Show selected pane, generally indicated by the fragment identifier, and hide all others. */
  var showActive = function(activeId) {
    $(".pane").hide();
    $(activeId).show();
  };
  
  var renderSeriesGraph = function(path) {
    return function(data) {
      nv.addGraph(function() {
        
        var fmt = function(d) {
          return d3.time.format("%Y-%m-%d")(new Date(d));
        };
        
        var chart = nv.models.lineWithFocusChart();
        chart.margin({top: 30, right: 35, bottom: 50, left: 60});
        chart.xAxis.tickFormat(fmt);
        chart.x2Axis.tickFormat(fmt);
      
        chart.yAxis.tickFormat(d3.format(',.2f'));
        chart.y2Axis.tickFormat(d3.format(',.2f'));
        
        d3.select(path)
          .datum(data)
          .transition()
          .duration(500)
          .call(chart);
      
        nv.utils.windowResize(chart.update);
      
        return chart;
      });
    }
  };
  
  /** Render the chart if it hasn't been already. */
  var fetchGraph = function(name) {
    var id = "#" + name;
    if(! $(id + " svg").children().length) {
      $.get("/" + name, renderSeriesGraph(id + " svg"));
    }
  };
  
  /** Set up click handlers and initialize panes. */
  (function() {
    $(window).bind("hashchange", function() {
      showActive(window.location.hash);
    });
    
    $("a[href=#streams]").click(function() { fetchGraph("streams") });
    
    $("a[href=#weighted]").click(function() { fetchGraph("weighted") });
    
    var hash = window.location.hash || "#problem";
    showActive(hash);
    if(hash != "#problem")
      fetchGraph(hash.substr(1));
  })();
});