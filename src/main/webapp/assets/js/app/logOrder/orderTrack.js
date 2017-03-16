define(['ajaxPackage', 'timePicker', 'table', 'jqueryConfirm'],
    function(Lprapm, timePicker) {
        var orderTrack = function() {
            var $table = $(".OrderTrack-table"), tableColumn = [];

            tableColumn = [{
                field: 'logisticsInfor_id',
                visible: false,
                title: 'id'
            },{
                field: 'order_id',
                visible: true,
                title: '订单ID'
            }, {
                field: 'courier_number',
                visible: true,
                title: '快递单号'
            }, {
                field: 'order_price',
                visible: true,
                title: '订单价格'
            }, {
                field: 'order_info',
                visible: true,
                title: '物流信息'
            } ];
            /*表格加载*/
            $table.bootstrapTable({
                singleSelect: false, //设置True 将禁止多选
                striped: true, //设置隔行变色
                clickToSelect: true, //设置true 将在点击行时，自动选择rediobox 和 checkbox
                sortable: true, //是否启用排序
                sortOrder: 'asc', //定义排序方式 'asc' 或者 'desc'
                sortName: 'order_id', //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                url: '/logisticsInfo/searchLogInfoValue', //请求接口
                columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
                detailView: true, //详细查看按钮
                detailFormatter: detailFormatter, //显示详细查看数据
                onLoadSuccess: function(response) { //请求成功，返回数据
                    // 数据操作
                    // 自动显示过长的数据
                    // console.log(response.data);
                    var arrow = $(".title-class");
                    $.each(arrow, function(index, val) {
                        /* iterate through array or object */
                        $(this).attr("title", val.innerText);
                    });
                }
            });

            function getColumns(params) {
                var columns = [];
                // columns.push(state);
                $.each(params, function(index, val) {
                    /* iterate through array or object */
                    var row = {};
                    row.field = val.field;
                    row.visible = val.visible;
                    row.title = val.title;
                    row.width = 150;
                    row.class = 'title-class';
                    row.sortable = true;
                    columns.push(row);
                });
                return columns;
            }

            function detailFormatter(index, row) {
                var html = [];
                $.each(tableColumn, function(index, val) {
                    /* iterate through array or object */
                    if (val["field"].indexOf('Id') < 0) {
                        $.each(row, function(key, value) {
                            /* iterate through array or object */
                            if (val["field"] == key) {
                                html.push('<p><b>' + val["title"] + ':</b> ' + value + '</p>');
                            }
                        });
                    }
                });
                return html.join('');
            }
        }

        return {
            "orderTrack": orderTrack
        }
    })