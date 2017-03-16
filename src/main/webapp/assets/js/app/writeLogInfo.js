/**
 * Created by Administrator on 2017/1/8.
 */
define(['ajaxPackage', 'moment', 'datepicker', 'jqueryValidate', 'angular', 'table', 'jqueryConfirm'],
    function(Lprapm, moment) {
        var $table = $(".user-table"),
            tableColumn = [],
            $addForm = $("#addUserForm"),
            isEdit = true,
            isFisrt = true,
            $modal = $("#myModal");
        var picker = $('#birthDay').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            dayViewHeaderFormat: 'MM YYYY',
            stepping: '2',
            showTodayButton: true,
            useCurrent: false,
        });
        picker.on('dp.change', function(e) {
            picker.data('DateTimePicker').hide();
        });
        var operateEvent = { //要放在commonrow之前，因为是赋值函数，要置前
            'click .orderDetail': function(event, value, row, index) {
                // console.log("edit:", row);
                orderDetailTable(row);
            },
            'click .edit': function(event, value, row, index) {
                // console.log("edit:", row);
                editTable(row);
            },
            'click .remove': function(event, value, row, index) {
                // console.log("remove:", row);
                $.confirm({
                    closeIcon: true,
                    closeIconClass: 'fa fa-close',
                    columnClass: 'small',
                    title: '删除',
                    content: '确定要删除这条记录吗？',
                    buttons: {
                        取消: {
                            btnClass: 'btn-default',
                        },
                        确定: {
                            btnClass: 'btn-success',
                            action: function() {
                                Lprapm.Ajax.request({
                                    url: '/logisticsInfo/deleteRecord',
                                    data: {
                                        "logisticsInfor_id": row.logisticsInfor_id
                                    },
                                    success: function(response) {
                                        if (response.success) {
                                            $table.bootstrapTable("refresh");
                                        } else {
                                            $.dialog('删除失败');
                                        }
                                    }
                                });
                            }
                        },
                    }
                });
            }
        }

        function orderDetailTable(row) {
            $.ajax({
                url: '/orders/searchOrders',
                data: {
                    "order_id": row.order_id
                },
                async:false,
                type: 'get',
                dataType: "json",
                success: function (response) {
                    if(response['success']){
                        $("#goodsName").html(response['data'][0]['goodsName'])
                        $("#goodsNumber").html(response['data'][0]['goodsNumber'])
                        $("#goodsVolume").html(response['data'][0]['goodsVolume'])
                        $("#goodsPerweight").html(response['data'][0]['goodsPerweight'])
                        $("#receiptName").html(response['data'][0]['receiptName'])
                        $("#receiptPhone").html(response['data'][0]['receiptPhone'])
                        $("#endTime").html(response['data'][0]['endTime'])
                        $("#orderAddress").html(response['data'][0]['orderAddress'])
                        $("#receiptAddress").html(response['data'][0]['receiptAddress'])
                        $("#myModal2").modal('show')
                    }
                },
                error: function () {
                    console.error("error show!");
                }
            });
        }

        function editTable(row) {
            isEdit = true;
            $("#myModalLabel").text("修改");
            showModal();
            $.each(row, function(index, value) {
                /* iterate through array or object */
                $addForm.find("input[name=" + index + "]").val(value);
                $addForm.find("textarea[name=" + index + "]").val(value);
            });
        }

        var commonrow = {
            field: 'operate',
            title: '操作',
            visible: true,
            width: '100px',
            align: 'center',
            events: operateEvent,
            formatter: operateFormatter
        };

        function operateFormatter(value, row, index) {
            return [
                '<a class="orderDetail" href="javascript:void(0)" title="物流详情">',
                '<i class="glyphicon glyphicon-file"></i>',
                '</a> ',
                '<a class="edit" href="javascript:void(0)" title="编辑">',
                '<i class="glyphicon glyphicon-edit"></i>',
                '</a> ',
                '<a class="remove" href="javascript:void(0)" title="删除">',
                '<i class="glyphicon glyphicon-remove"></i>',
                '</a>'
            ].join("");
        }

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
            sortName: 'order_id', //定义排序列,
            url: '/logisticsInfo/searchLogInfoValue', //请求接口
            columns: getColumns(tableColumn), //列数据,也可以通过函数来获取
            onDblClickRow: function(row, $element, field) {
                editTable(row);
            },
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
            $.each(params, function(index, val) {
                /* iterate through array or object */
                var row = {};
                row.field = val.field;
                row.visible = val.visible;
                row.title = val.title;
                row.width = "100";
                row.class = 'title-class';
                row.sortable = true;
                columns.push(row);
            });
            columns.push(commonrow);
            return columns;
        }


        function showModal() {
            $modal.modal("show");
            // 设置弹框高度
            var bodyHeight = window.screen.height;
            $modal.find('.modal-body').css({
                overflowY:'scroll',
                height: bodyHeight - 500 + 'px'
            });
        }

        /*关闭重置表单*/
        $modal.on('hide.bs.modal', function() {
            $("#resetBtn").click();
        });
        $("#submitBtn").click(function(event) {
            /* 点击提交按钮 */
            submitData();
        });

        function submitData() {
            var formData = {};
            formData = $addForm.serializeArray();
            // console.log("formData:", formData);
            Lprapm.Ajax.request({
                url: '/logisticsInfo/editLogInfo' ,
                data: formData,
                success: function(response) {
                    if (response.success) {
                        $table.bootstrapTable("refresh");
                        $modal.modal('hide');
                    } else {
                        $.dialog(response.messages);
                    }
                }
            });
        }
    });
