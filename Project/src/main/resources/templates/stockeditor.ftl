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

    <h2>Manage stock</h2>
    <form action="/saveChanges" method="post">
        <table>
            <thead>
                <td>Stock name</td>
                <td>Last Price</td>
                <td>Amount</td>
            </thead>

            <#list model.editable as key, stockPartInfo>
                <tr>
                    <td>${key}</td>
                    <td>${stockPartInfo.price}</td>
                    <td>
                        <div class="input-group">
                            <#assign stockId = idname + idx>
                            <#assign hiddenValue = '${key};${stockId}'>
                            <input type="button" value="-" class="button-minus" data-field="${stockId}">
                            <input type="number" step="1" max="" value="${stockPartInfo.amount}" name="${stockId}" class="quantity-field">
                            <input type="button" value="+" class="button-plus" data-field="${stockId}">
                            <input type="hidden" value="${hiddenValue}">
                        </div>
                    </td>
                </tr>
                <#assign  idx = idx + 1>
            </#list>
        </table>
        <button type="submit">Change</button>
    </form>

    <h2>Add stock</h2>
    <form action="/addStock" method="post">
        <table>
            <#list model.tickerToStockName as key, value>
                <#if key?? && value??>
                    <tr>
                        <td>${value}</td>
                        <td><input type="checkbox" name="${key}" required="false"></td>
                    </tr>
                </#if>
            </#list>
        </table>
    </form>




    <script type="text/javascript"  src="webjars/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>