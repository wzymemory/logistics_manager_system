function selectRule() {
    var dangerGoodsName = $("#dangerGoodsName").val();
    $("#selectRuleResult").html("")
    if(dangerGoodsName!=""){
        $.ajax({
            url: '/home/selectRule',
            async:false,
            data: {"dangerGoodsName":dangerGoodsName},
            type: 'get',
            dataType: "json",
             success: function (response) {
                if(response['success']){
                    if(response['total']==0){
                        $("#selectRuleResult").html("<p style='color: #2fd4ff;font-size: 20px'>该物品的任何类型都可以被邮寄！</p>")
                    }else{
                        var tableContent = ""
                        for(var i=0;i<response['total'];i++){
                            var userNeedToKnow =  response['data'][i]['userNeedToKnow']
                            if(userNeedToKnow==null){
                                userNeedToKnow=""
                            }
                            tableContent = tableContent+"<tr><td>"+response['data'][i]['dangerGoods_type']+"</td><td>"+response['data'][i]['dangerGoods_name']+"</td><td>"+response['data'][i]['transportationByLand']+"</td><td>"+response['data'][i]['transportationByAir']+"</td><td>"+userNeedToKnow+"</td></tr>"
                        }
                        console.log(tableContent)
                        $("#selectRuleResult").html(
                            "<table class='table table-bordered table-hover table-condensed'>"+
                            "<thead><tr> <th>物品类型</th> <th>物品名称</th><th>陆运</th> <th>航空</th><th>客户需知</th></tr> </thead>" +
                            "<tbody>"+tableContent +"</tbody>" +
                            "</table>")
                    }
                }else{
                    $("#selectRuleResult").html("<p style='color: red;font-size: 20px'>查询过程出现了点小问题，请稍后再试！</p>")
                }
             },
             error: function () {
                console.error("error show!");
             }
         });
    }

}

function selectRange(){
    $("#selectRangeResult").html("");
    if($("#addressRange").val()!=""){
        $("#selectRangeResult").html("<p style='color: #2fd4ff;font-size: 20px'>全境配送！</p>");
    }

}

function selectTime(){
    $("#selectTimeResult").html("")
    var fromAddress =  $("#fromAddress").val();
    var toAddress= $("#toAddress").val();
    var goodWeight = $("#goodWeight").val();
    if(fromAddress!="" && toAddress!="" && goodWeight>0){
        $.ajax({
            url: '/home/selectTime',
            async:false,
            data: {"fromAddress":fromAddress,"toAddress":toAddress,"goodWeight":goodWeight},
            type: 'get',
            dataType: "json",
            success: function (response) {
                if(response['success']){
                    var tableContent = ""
                    tableContent = tableContent+"<tr><td>"+response['data']['productName']+"</td><td>"+response['data']['weightNum']+"</td><td>"+response['data']['needTime']+"</td><td>"+response['data']['needPrice']+"</td></tr>"
                     $("#selectTimeResult").html(
                            "<table class='table table-bordered table-hover table-condensed'>"+
                            "<thead><tr> <th>我们的产品</th> <th>物品重量</th><th>预计时效</th> <th>寄付价</th></tr> </thead>" +
                            "<tbody>"+tableContent +"</tbody>" +
                            "</table>")
                }else{
                    $("#selectTimeResult").html("<p style='color: red;font-size: 20px'>查询过程出现了点小问题，请稍后再试！</p>")
                }
            },
            error: function () {
                console.error("error show!");
            }
        });
    }
}

function orderTrack(){
    $("#orderTrackResult").html("")
    var courier_number = $("#courier_number").val();
    if(courier_number!=""){
        $.ajax({
            url: '/logisticsInfo/orderTrack',
            async:false,
            data: {"courier_number":courier_number},
            type: 'get',
            dataType: "json",
            success: function (response) {
                if(response['success']){
                    if(response['data']!=null){
                        var tableContent = ""
                        var order_status=""
                        if(response['data']['order_state']==0){
                            order_status = "运输中"
                        }
                        if(response['data']['order_state']==1){
                            order_status = "已签收"
                        }
                        if(response['data']['order_state']==-1){
                            order_status = "订单已失效"
                        }
                        tableContent = tableContent+"<tr><td>"+response['data']['courier_number']+"</td><td>"+order_status+"</td><td>"+response['data']['order_price']+"</td><td>"+response['data']['order_info']+"</td></tr>"
                        $("#orderTrackResult").html(
                            "<table class='table table-bordered table-hover table-condensed'>"+
                            "<thead><tr> <th>快递单号</th> <th>快递状态</th><th>物流费用</th> <th>物流信息</th></tr> </thead>" +
                            "<tbody>"+tableContent +"</tbody>" +
                            "</table>")
                    }else{
                        $("#orderTrackResult").html("<p style='color: red;font-size: 20px'>查询的快递不存在，请核对您的快递单号！</p>")
                    }

                }else{
                    $("#orderTrackResult").html("<p style='color: red;font-size: 20px'>查询过程出现了点小问题，请稍后再试！</p>")
                }
            },
            error: function () {
                console.error("error show!");
            }
        });
    }
}