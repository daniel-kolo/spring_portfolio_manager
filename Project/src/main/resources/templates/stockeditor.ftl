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
    <div class="input-group">
        <input type="button" value="-" class="button-minus" data-field="quantity">
        <input type="number" step="1" max="" value="1" name="quantity" class="quantity-field">
        <input type="button" value="+" class="button-plus" data-field="quantity">
    </div>

    <script type="text/javascript"  src="webjars/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>