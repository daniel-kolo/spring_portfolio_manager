<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Stock editing</title>
    <style>
        input,
        textarea {
            border: 1px solid #eeeeee;
            box-sizing: border-box;
            margin: 0;
            outline: none;
            padding: 10px;
        }

        input[type="button"] {
            -webkit-appearance: button;
            cursor: pointer;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
        }

        .input-group {
            clear: both;
            margin: 15px 0;
            position: relative;
        }

        .input-group input[type='button'] {
            background-color: #eeeeee;
            min-width: 38px;
            width: auto;
            transition: all 300ms ease;
        }

        .input-group .button-minus,
        .input-group .button-plus {
            font-weight: bold;
            height: 38px;
            padding: 0;
            width: 38px;
            position: relative;
        }

        .input-group .quantity-field {
            position: relative;
            height: 38px;
            left: -6px;
            text-align: center;
            width: 62px;
            display: inline-block;
            font-size: 13px;
            margin: 0 0 5px;
            resize: vertical;
        }

        .button-plus {
            left: -13px;
        }

        input[type="number"] {
            -moz-appearance: textfield;
            -webkit-appearance: none;
        }

        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=password], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4F81BC;
            color: white;
            padding-left: 25%;
            padding-right: 25%;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #4F81BC;
        }

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
    <#assign idx = 0>
    <#assign idname = 'stockCount'>
    <script>
        window.onload = function () {
            function incrementValue(e) {
                e.preventDefault();
                var fieldName = $(e.target).data('field');
                var parent = $(e.target).closest('div');
                var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

                if (!isNaN(currentVal)) {
                    parent.find('input[name=' + fieldName + ']').val(currentVal + 1);
                } else {
                    parent.find('input[name=' + fieldName + ']').val(0);
                }
            }

            function decrementValue(e) {
                e.preventDefault();
                var fieldName = $(e.target).data('field');
                var parent = $(e.target).closest('div');
                var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

                if (!isNaN(currentVal) && currentVal > 0) {
                    parent.find('input[name=' + fieldName + ']').val(currentVal - 1);
                } else {
                    parent.find('input[name=' + fieldName + ']').val(0);
                }
            }

            $('.input-group').on('click', '.button-plus', function(e) {
                incrementValue(e);
            });

            $('.input-group').on('click', '.button-minus', function(e) {
                decrementValue(e);
            });
        }
    </script>
</head>
<body>
<div style="background: #333; padding-left: 20%; padding-right: 20%; align-content: center">
    <ul style="background: #4F81BC; max-width: 1200px">
            <li><a href="/welcome">Back</a></li>
    </ul>
    <div style="max-width: 1200px; background: white; align-content: center">
        <ul style="background: black; max-width: 1200px">
            <li><h2 style="color: white;">Manage stock</h2></li>
        </ul>
        <form action="/saveChanges" method="post">
            <table style="width: 100%; padding-left: 20%;">
                <thead>
                <td>Stock name</td>
                <td>Last Price</td>
                <td>Amount</td>
                </thead>
                <#if model?? && model.editable??>
                    <#list model.editable as key, editView>
                        <tr>
                            <td>${key}</td>
                            <td>${editView.price}</td>
                            <td>
                                <div class="input-group">
                                    <#assign stockId = idname + idx>
                                    <#assign hiddenValue = '${stockId}_${editView.tickerId}'>
                                    <input type="button" value="-" class="button-minus" data-field="${hiddenValue}">
                                    <input type="number" step="1" max="" value="${editView.amount}" name="${hiddenValue}" class="quantity-field">
                                    <input type="button" value="+" class="button-plus" data-field="${hiddenValue}">
                                    <input type="hidden" value="${hiddenValue}">
                                </div>
                            </td>
                        </tr>
                        <#assign  idx = idx + 1>
                    </#list>
                </#if>
            </table>
            <input type="submit">
        </form>
        <ul style="background: black; max-width: 1200px">
            <li><h2 style="color: white;">Add stock</h2></li>
        </ul>
        <form action="/addStock" method="post">
            <table style="width: 100%; padding-left: 25%; padding-right: 25%">
                <#list model.tickerToStockName as key, value>
                    <#if key?? && value??>
                        <tr>
                            <td>${value}</td>
                            <#assign stockId =  'stockId;' + key>
                            <td><input type="checkbox" name="${stockId}"></td>
                        </tr>
                    </#if>
                </#list>
            </table>
            <input type="submit">
        </form>
    </div>
</div>
    <script type="text/javascript"  src="webjars/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>