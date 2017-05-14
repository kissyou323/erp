/**
 Created by Administrator on 2016/7/4.
 */

suffix = ".png";

//开发
//var url_server = "http://127.0.0.1:8080";
//var img_server = "http://127.0.0.1:8080/img/upload/";
//var qrcode_server = "http://127.0.0.1:8080/img/qrcode/"

//联调
var url_server = "http://127.0.0.1:8080";
var img_server = "http://127.0.0.1:8080/img/upload/";
var qrcode_server = "http://127.0.0.1:8080/img/qrcode/"
var erp_title = "XXX派出所ERP管理系统";

//徽城
//var url_server = "http://10.128.30.5:8080";
//var img_server = "http://10.128.30.5:8080/img/upload/";
//var qrcode_server = "http://10.128.30.5:8080/img/qrcode/";
//var erp_title = "歙县徽城派出所ERP管理系统";

//岩寺
//var url_server = "http://10.128.12.171:8080";
//var img_server = "http://10.128.12.171:8080/img/upload/";
//var qrcode_server = "http://10.128.12.171:8080/img/qrcode/";
//var erp_title = "徽州区岩寺派出所ERP管理系统";





/**
 * 资产和装备
 * @param str 状态（1-库存、2-完好、3-借出、4-报废、5-维修检验、6-未入库）
 * @returns {string}
 */
function str_State(str){
    var state = "";
    if (str == 1) {
        state = "库存";
    } else if (str == 2) {
        state = "完好";
    } else if (str == 3) {
        state = "借出";
    } else if (str == 4) {
        state = "报废";
    } else if (str == 5) {
        state = "维修检验";
    } else if (str == 6) {
        state = "未入库";
    }
    return state;
}

/**
 * 案卷状态（1-未结案、2-已结案 3案件撤销 4案件合并）
 * @param str
 */
function caseStatus(str){
    var state = "";
    if (str == 1) {
        state = "未结案";
    } else if (str == 2) {
        state = "已结案";
    } else if (str == 3) {
        state = "案件撤销";
    } else if (str == 4) {
        state = "案件合并";
    }
    return state;
}

/**
 * 档案状态(1-还入 2 -建档 5 -归档3借出4其他6-移交)
 * @param str
 */
function fileStatus(str){
    var state = "";
    if (str == 1) {
        state = "还入";
    } else if (str == 2) {
        state = "建档";
    } else if (str == 3) {
        state = "借出";
    } else if (str == 4) {
        state = "其他";
    } else if (str == 5) {
        state = "归档";
    } else if (str == 6) {
        state = "移交";
    }
    return state;
}

/**
 *  案件类型（行政、刑事）
 * @param str
 */
function fileStyle(str){
    var state = "";
    if (str == 1) {
        state = "行政";
    } else if (str == 2) {
        state = "刑事";
    }
    return state;
}

/**
 * 是否已经督案0未督案1已经督案
 * @param str
 */
function isCaseList(str){
    var state = "";
    if (str == 0) {
        state = "未督案";
    } else if (str == 1) {
        state = "已督案";
    }
    return state;
}

/**
 *   结案状态1-未结案2-结案审核中3-已结案
 * @param str
 */
function endcaseStatus(str){
    var state = "";
    if (str == 1) {
        state = "未结案";
    } else if (str == 2) {
        state = "结案审核中";
    } else if (str == 3) {
        state = "已结案";
    } else if (str == 4) {
        state = "已驳回";
    }
    return state;
}

/**
 * 督案单状态1-审核中2-正在执行-3已完成4-未完成5-驳回
 */
function caseListState(str){
    var state = "";
    if (str == 1) {
        state = "审核中";
    } else if (str == 2) {
        state = "正在执行";
    } else if (str == 3) {
        state = "已完成";
    } else if (str == 4) {
        state = "未完成";
    } else if (str == 5) {
        state = "驳回";
    }
    return state;
}

/**
 * 涉案财物
 * @param str 状态（1-库存3-借出2-持有人领回4-未入库5-移交）
 */
function possessions_str(str){
    var state = "";
    if (str == 1) {
        state = "库存";
    } else if (str == 2) {
        state = "持有人领回";
    } else if (str == 3) {
        state = "借出";
    } else if (str == 4) {
        state = "未入库";
    } else if (str == 5) {
        state = "移交";
    }
    return state;
}

function isStrNull(str){
    if (str == null || str == "" || str == "undefined") {
        return "";
    } else {
        return str;
    }
}

function updateStatus(){
    BUI.Message.Alert('只有 库存 | 完好 | 未入库 状态的记录才能修改！');
}

function updateCaseStatus(){
    BUI.Message.Alert('归档 | 借出 | 移交 的案件无法修改');
}

function repairStatus(){
    BUI.Message.Alert('只有维修中的记录才能修改！');
}

function updatePossessionsStatus(){
    BUI.Message.Alert('非库存状态的涉案财物无法修改！');
}