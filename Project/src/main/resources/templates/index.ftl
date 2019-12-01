<!DOCTYPE html>
<html lang="en" xmlns:mvc="http://www.w3.org/1999/xlink">
<head>
    <meta charset="UTF-8">
    <title>Stonks the investment manager</title>
    <#assign containerName = 'container'>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
    </style>
    <script>

        window.onload = function () {
            function genChart (idToReplace, stockName, dataArray) {
                console.log(dataArray);
                var chart = new CanvasJS.Chart(idToReplace, {
                    animationEnabled: true,
                    title: {
                        text: stockName
                    },
                    axisX: {
                        valueFormatString: "MMM YY"
                    },
                    axisY: {
                        title: "Price",
                        titleFontColor: "#4F81BC",
                        suffix: "$"
                    },
                    data: [{
                        indexLabelFontColor: "darkSlateGray",
                        name: "views",
                        type: "area",
                        dataPoints: dataArray
                    }]
                });
                return chart;
            }

            var charts = []
            var dict = {};
            /*Convert the model to js objects*/
            <#list model.stocksInfo as key, stockInfo>
                var data = [];
                var idx = 0;
                    <#list stockInfo.elementsToShow as element>
                        var dateSplit = '${element.dateDownload?string('dd/MM/yyyy')}'.split('/');
                        console.log(dateSplit);
                        data.push({
                            x     : idx,
                            y     : ${element.price},
                            label : '${element.dateDownload?string('dd/MM/yyyy')}'
                        })
                        idx++;
                    </#list>
                dict['${key}'] = data;
            </#list>
            var stockKeys = [<#list model.stocksInfo as key, val>'${key}',</#list>];
            for (var i = 0; i < stockKeys.length; i++) {
                var chartId = String('${containerName}' + i);
                var currentKey = stockKeys[i];
                var currentData = dict[currentKey];
                var chart = genChart(chartId, currentKey, currentData);
                chart.render();
            }
        }




    </script>
</head>
<body>

    <ul>
        <h1 style="color: white">STONKS the investments manager</h1>
    </ul>
    <ul>
        <li><a class="active" href="/welcome">Home</a></li>
        <li><a href="#about">About</a></li>
    </ul>
    <ul style="background: #4F81BC">
        <#if model.user??>
            <li><a href="/stockEdit">Manage stocks</a></li>
            <li><a href="/welcome">Reporting</a></li>
            <li><a href="/quit">Logout</a></li>
        <#else>
            <li><a href="/register">Register</a></li>
            <li><a href="/login">Login</a></li>
        </#if>
    </ul>
    <#if model.stocksInfo??>
        <#assign id = 0>
        <#list model.stocksInfo as key, value>
            <#assign containerid = containerName + id>
                <div id="${containerid}" style="height: 370px; max-width: 920px; margin: 0px auto; background: black"></div>
            <#if model.user??>
                <div style="max-height: 370px; max-width: 920px; margin: 0px auto; background: #333; color: white">
                    <table style="object-fit: fill;">
                        <tr>
                            <td>Current Price: </td>
                            <td>${value.lastPrice}</td>
                        </tr>
                        <tr>
                            <td>Amount: </td>
                            <td>${value.amount}</td>
                        </tr>
                        <tr>
                            <td>Total value: </td>
                            <td>${value.amount * value.lastPrice}</td>
                        </tr>
                    </table>
                </div>
            </#if>
            <#assign id = id + 1>
        </#list>

    <#else>
                <ul style="background: red">
                    <li>No data present</li>
                </ul>
    </#if>

    <script src="/js/canvasjs.min.js"></script>
</body>
</html>