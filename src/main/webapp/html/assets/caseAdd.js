/**
 * 
 */


function showTime(){
    var caseTime = {
        elem: '#caseTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2000-01-01 00:00:00', //设定最小日期为当前日期 laydate.now()
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: true,
        choose: function (datas) {
            endTime.min = datas; //开始日选好后，重置结束日的最小日期
            endTime.start = datas //将结束日的初始值设定为开始日
        }
    };
    var startTime = {
        elem: '#startTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2000-01-01 00:00:00', //设定最小日期为当前日期 laydate.now()
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: true,
        choose: function (datas) {

        }
    };
    var endTime = {
        elem: '#endTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2000-01-01 00:00:00', //设定最小日期为当前日期 laydate.now()
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: true,
        choose: function (datas) {
            caseTime.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(caseTime);
    laydate(startTime);
    laydate(endTime);
}
showTime();

$(function(){
	
	$(".possessionsList").click(function(){
        window.open ('../page/Possessions/linkPossessionsList.html','newwindow','height=500,width=600,top=200,left=200,toolbar=no,menubar=no,scrollbars=yes,location=no,status=no,alwaysRaised=yes,resizable=no')
    });
	
    $(".button-primary").click(function(){
        if ($(".caseNo").val() == "" || $(".caseName").val() == "" || $(".policeName").val() == "" || $(".caseTime").val() == "" || $(".startTime").val() == "") {
            BUI.Message.Alert('编号|名称|办案民警|入库时间|案发时间|都不能为空');
        } else if ($(".caseStatus").val() == 2 && $(".endTime").val() == "") {
        	BUI.Message.Alert('已结案的案件结案时间不能为空');
        } else {
            var caseDto = {
                "caseNo": $(".caseNo").val(),
                "caseName": $(".caseName").val(),
                "policeLoginName": $(".policeLoginName").val(),
                "policeName": $(".policeName").val(),
                "possessionsNo": $(".possessionsNo").val(),
                "possessionsName": $(".possessionsName").val(),
                "caseAddress": $(".caseAddress").val(),
                "caseStatus": $(".caseStatus").val(),
                "fileStatus": $(".fileStatus").val(),
                "style": $(".style").val(),
                "caseTime": $(".caseTime").val(),
                "startTime": $(".startTime").val(),
                "endTime": $(".endTime").val(),
                "caseExplain": $(".caseExplain").val(),
                "suspectDtoList" : [
                    {
                        suspectName:document.getElementById("addSuspects").getElementsByTagName("input")[0].value,
                        mobileNo:document.getElementById("addSuspects").getElementsByTagName("input")[1].value,
                        identity:document.getElementById("addSuspects").getElementsByTagName("input")[2].value,
                        remarks:document.getElementById("addSuspects").getElementsByTagName("input")[3].value
                    },{
                        suspectName:document.getElementById("addSuspects").getElementsByTagName("input")[4].value,
                        mobileNo:document.getElementById("addSuspects").getElementsByTagName("input")[5].value,
                        identity:document.getElementById("addSuspects").getElementsByTagName("input")[6].value,
                        remarks:document.getElementById("addSuspects").getElementsByTagName("input")[7].value
                    },{
                        suspectName:document.getElementById("addSuspects").getElementsByTagName("input")[8].value,
                        mobileNo:document.getElementById("addSuspects").getElementsByTagName("input")[9].value,
                        identity:document.getElementById("addSuspects").getElementsByTagName("input")[10].value,
                        remarks:document.getElementById("addSuspects").getElementsByTagName("input")[11].value
                    },{
                        suspectName:document.getElementById("addSuspects").getElementsByTagName("input")[12].value,
                        mobileNo:document.getElementById("addSuspects").getElementsByTagName("input")[13].value,
                        identity:document.getElementById("addSuspects").getElementsByTagName("input")[14].value,
                        remarks:document.getElementById("addSuspects").getElementsByTagName("input")[15].value
                    },{
                        suspectName:document.getElementById("addSuspects").getElementsByTagName("input")[16].value,
                        mobileNo:document.getElementById("addSuspects").getElementsByTagName("input")[17].value,
                        identity:document.getElementById("addSuspects").getElementsByTagName("input")[18].value,
                        remarks:document.getElementById("addSuspects").getElementsByTagName("input")[19].value
                    }
                ]

            };
            $.ajax({
                type: "POST",
                url: url_server + "/erp/case/save",
                data:  JSON.stringify(caseDto),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code == 501) {
                        BUI.Message.Alert('编号已存在！');
                    } else if(data.code == 5001) {
                        BUI.Message.Alert('二维码生成失败,请联系管理员！');
                    } else if(data.code == 201) {
                        BUI.Message.Alert('请求异常,异常代码为201！');
                    }else if(data.code == 200) {
                        location.href = "success.html"
                    } else {
                        BUI.Message.Alert('用户登录超时!!请重新登录');
                    }
                },
                error: function () {
                    alert("请求失败");
                }
            });
        }
    });
    $(".style").change(function(){
        if ($(".style").val() == 2) {
            $("#addSuspects").css("display","block");
            $(".suspectsbtn").css("display","block");
        } else  {
            $("#addSuspects").css("display","none");
            $(".suspectsbtn").css("display","none");
        }
    });

    $(".policeName").click(function(){
        window.open ('../page/user/userDetail.html','newwindow','height=500,width=600,top=200,left=200,toolbar=no,menubar=no,scrollbars=yes,location=no,status=no,alwaysRaised=yes,resizable=no')
    });
    
  
})

function delName() {
	var pName = "";
	var pNo = "";
	var possessionsNo = $(".possessionsNo").val();
	var possessionsName = $(".possessionsName").val();
	var posNames = possessionsName.split(";");
	var posNos = possessionsNo.split(";");
	var posNo = "";
	var posName = "";
	for (var i = 0 ;i < posNames.length ; i ++ ) {
		if (posNames[i] != "") {
			if (posNames[i] !=  pName) {
				posName = posName + posNames[i] + ";";
			}
		}
	}
	 $(".possessionsName").val(posName);
	 
	 for (var i = 0 ;i < posNos.length ; i ++ ) {
			if (posNos[i] != "") {
				if (posNos[i] !=  pNo) {
					posNo = posNo + posNos[i] + ";";
				}
			}
		}
	 $(".possessionsName").val(posNo);
	 /**以上是去掉删除的涉案财物**/
	 var new_posNo = $(".possessionsNo").val();
	 var new_posName = $(".possessionsName").val();
	 var new_posNames = new_posName.split(";");
	 var new_posNos = new_posNo.split(";");
	 var html  = "";
	 for (var i = 0 ;i < new_posNames.length ; i ++ ) {
 		if (new_posNames[i] != "") {
 			html = html + "<p>"+ new_posNos[i] +" &nbsp;&nbsp;"+ new_posNames[i] +"<a onclick=\"delName("+new_posNames[i]+","+ new_posNos[i]+")\">&nbsp;刪除</a></p>";	
 		}
 	}
	 $("#possessionsNameList").val(html);
	 
	 
}



