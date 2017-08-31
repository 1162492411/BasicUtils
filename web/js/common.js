
//--------------公共函数------------------//


/**
 * 检测数据是否为空
 * @param data 待检测的数据
 * @returns {boolean}
 */
//TODO:此处待完善
function checkData(data) {
    return data == '' || data == '[]' || !data || data == 0 || data == "{}";
}

function checkElementById(id) {
    return document.getElementById(id).length > 0;
}


/**
 * 生成对象,返回字符串形式,用以向后台发送数据
 * @param fields 属性数组
 * @param values 值数组
 * @param check 是否检查值为空:当检查时，若值为空，则不生成该项属性及值
 */
function constructor(fields,values,check){
    var obj = new Object();
    for(var i=0; i < fields.length; i++){
        if(!check || (check && !checkData(values[i])))
            obj[fields[i]] = values[i];
    }
    return JSON.stringify(obj);
}

/**
 * 初始化分页
 * currentPage : 当前页数
 * totalPage : 总页数
 * id : 分页按钮的id
 * path : 每个分页按钮的共同url部分
 */
function initPag(currentPage, totalPage, id, path) {
    var c = parseInt(currentPage);
    var t = parseInt(totalPage);
    var prevString = "";
    var suffString = "";
    if (t <= 1) { //总页数过少时移除分页
        $("#" + id).remove();
        return;
    }
    //处理"上一页"
    if (c > 1) $("#" + id).append("<li id='pagLast' ><a href='" + path + "/" + (c - 1) + "'> << </a></li>");
    //添加当前页码前的页数
    if (c > 3) {
        prevString += "<li><a>...</a></li>";
        for (var i = c - 3; i < c; i++) prevString += "<li><a href='" + path + "/" + i + "'>" + i + "</a></li>";
        $("#" + id).append(prevString);
    } else {
        for (var i = 1; i < c; i++) prevString += "<li><a href='" + path + "/" + i + "'>" + i + "</a></li>";
        $("#" + id).append(prevString);
    }
    //添加当前页码
    $("#" + id).append("<li id='pagCurrent' class='active'><span>" + c + "</span></li>");
    //添加当前页码后的页数
    if (c < t - 3) {
        for (var j = c + 1; j <= c + 3; j++) suffString += "<li><a href='" + path + "/" + j + "'>" + j + "</a></li>";
        suffString += "<li><a>...</a></li>";
    } else
        for (var j = c + 1; j <= t; j++) suffString += "<li><a href='" + path + "/" + j + "'>" + j + "</a></li>";
    $("#" + id).append(suffString);
    //处理"下一页"
    if (c < t) $("#" + id).append("<li id='pagNext' ><a href='" + path + "/" + (c + 1) + "'> >> </a></li>");
}


//------------无限级联动---------------------//
/**
 * 初始化首个下拉框--预设置能同时存在十个下拉框
 */
function initFirstSelection() {
    var levels = ["firstLevel","secondLevel","thirdLevel","fourthLevel","fifthLevel","sixthLevel","seventhLevel","eighthLevel","ninthLevel","tenthLevel"];
    $.ajax({
        type: 'GET',
        url: '/api/getFirstTags',
        async : false,
        success: function(data) {
            initSelections(levels[0],JSON.parse(data));
        }
    });
    initChildSelections(levels,0);
}

/**
 * 为下拉框添加数据
 * @param id 下拉框的id
 * @param data 待添加的数据
 */
function initSelections(id,data) {
    if(!checkData(data)){
        if(!checkElementById(id))
            $("#tagPanel").append("<select id='" + id + "'></select>");
        var $selection = $("#" + id);
        var content = "<option value='0'>请选择</option>";
        for(var i = 0; i < data.length; i++)
            content += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
        $selection.append(content);
    }
}

/**
 * 为选择框添加子框数据
 * @param levels 总levels数据
 * @param current 当前选择框位于levels中的位置
 */
function initChildSelections(levels,current) {
    $("#" + levels[current]).change(function () {
        $("#tid").attr("value",$("#" + levels[current] + " option:selected").val());
        $.ajax({
            type: 'GET',
            url: '/api/getChildrenTags/' + $("#" + levels[current] + " option:selected").val(),
            async : false,
            success: function(data) {
                resetChildSelections(levels,current + 1);
                initSelections(levels[current + 1],JSON.parse(data));
                initChildSelections(levels,current + 1);
            }
        });
    });
}

/**
 * 删除选择框的所有子框
 * @param levels 总levels数据
 * @param staret 选择框位于levels中的位置,该位置以后的所有子框均删除
 */
function resetChildSelections(levels,start){
    for(var i = start; i < levels.length; i++){
        if(checkElementById(levels[i]))
            $("#" + levels[i]).remove();
    }
}

//----------------过滤类函数------------------///

/**
 * 过滤回车符
 * @param content 待过滤的数据
 */
function filterEnter(content) {
    var string = content;
    try {
        string = string.replace(/\n/g, "<br />");
    } catch (e) {
        alert(e.message);
    }
    return string;
}


//----------------文件类函数----------------------//
/**
 * 上传文件
 *假定页面中有以下代码
 * <input class="btn btn-primary"  value="添加附件" id="uploadBtn" onclick="uploadFile()" />
 * <input type="file" class="btn" accept=".zip,.rar,.7z" value="选择文件" id="codeFile"  />
 */
function uploadFile() {
    var formData = new FormData();
    formData.append('file', $('#codeFile')[0].files[0]);
    $("#uploadBtn").attr("value", "上传中...");
    $.ajax({
        url: '/file/upload/file',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            if (data == "error") {
                $("#uploadBtn").attr("class", "btn btn-danger");
                $("#uploadBtn").attr("value", "上传失败");
            } else {
                $("#uploadBtn").attr("class", "btn btn-success");
                $("#uploadBtn").attr("value", "上传成功");
                $("#attid").attr("value", data);
            }
        },
        error: function() {
            alert('发送请求失败！');
        }
    });
}


