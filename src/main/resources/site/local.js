// set the dimensions and margins of the graph
var margin = { top: 10, right: 30, bottom: 30, left: 60 },
    width = 1100 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

// append the svg object to the body of the page
var svg = d3.select("#my_dataviz")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform",
        "translate(" + margin.left + "," + margin.top + ")");

//Read the data
d3.json("/api/charts",
    // Now I can use this dataset:
    function (data) {

        //format date
        var parseDate = d3.timeParse("%Y-%m-%d");
        data = data.map(function (d) {
            d.week = parseDate(d.week);
            return d;
        });

        // filter data
        data = data.filter(function (d) { return d.position != null; });

        // Add X axis --> it is a date format
        var x = d3.scaleTime()
            .domain(d3.extent(data, function (d) { return d.week; }))
            .range([0, width]);
        svg.append("g")
            .attr("transform", "translate(0," + height + ")")
            .attr("stroke", "#ffffff")
            .attr("stroke-width", 0.5)
            .style("fill", "#ffffff")
            .call(d3.axisBottom(x)
                .tickFormat(d3.timeFormat("%Y-%m-%d")))
                //.tickFormat(d3.timeFormat("%d-%m")) 
                //.ticks(data.length)


        // Add Y axis
        var y = d3.scaleLinear()
            .domain(d3.extent(data, function (d) { return d.position; }))
            .range([0, height]);
        svg.append("g")
            .attr("stroke", "#ffffff")
            .attr("stroke-width", 0.5)
            .style("fill", "#ffffff")
            .call(d3.axisLeft(y));

        // Add the line
        svg.append("path")
            .datum(data)
            .attr("fill", "none")
            .attr("stroke", "#8CB9D8")
            .attr("stroke-width", 2.5)
            .attr("d", d3.line()
                .x(function (d) { return x(d.week) })
                .y(function (d) { return y(d.position) })
            )


        // Add the points
        svg
            .append("g")
            .selectAll("dot")
            .data(data)
            .enter()
            .append("circle")
            .attr("cx", function (d) { return x(d.week) })
            .attr("cy", function (d) { return y(d.position) })
            .attr("r", 4)
            .attr("fill", "#fff400")
    })