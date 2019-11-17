<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Stonks the investment manager</title>
</head>
<body>
    <h1>STONKS the investments manager</h1>
    <p>Current stocks</p>
    <table>
        <tr>
            <th>NAME</th>
            <th>DATE</th>
            <th>PRICE</th>
        </tr>
        <#list stocks as stock>
            <tr>
                <td>${stock.name}</td>
                <td>${stock.date?string('dd/MM/yyyy')}</td>
                <td>${stock.price}</td>
            </tr>
        </#list>
    </table>
</body>
</html>