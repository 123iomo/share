function allowAliwwShieldWord(content) {
    if (content.indexOf('好评') >= 0
        || content.indexOf('确认收货') >= 0
        || content.indexOf('qq') >= 0
        || content.indexOf('扣扣') >= 0
        || content.indexOf('五星') >= 0
        || content.indexOf('群') >= 0
        || content.indexOf('http://bangpai.taobao.com') >= 0
        )
        return confirm('内容中包含以下词汇，发旺旺时，容易被淘宝屏蔽：\r\n好评、确认收货、qq、扣扣、五星、群、帮派链接\r\n是否继续？');
    return true;
}

function testEmail(email){
    var isEmail1 = /^\w+([\.\-]\w+)*\@\w+([\.\-]\w+)*\.\w+$/;
    var isEmail2 = /^.*@[^_]*$/;
    return isEmail1.test(email) && isEmail2.test(email);
}

function testMobile(mobile) {
    var isMobile = /^1[34578]\d{9}$/g;
    return isMobile.test(mobile);
}

function testInteger(intStr){
    var isInteger = /^\d+$/;
    return isInteger.test(intStr);
}

function ajaxResponseResult(data) {
    if (data.AlertMsg)
        alert(data.AlertMsg);
    if (data.PromptMsg)
        showPrompt(data.PromptMsg);
    if (data.DialogBody)
        showExceptionMsgDialog(data.DialogBody, data.DialogTitle);
    if (data.responseText)
        showExceptionMsgDialog(data.responseText);
    if (data.indexOf && data.indexOf("GenericErrorPage.aspx?aspxerrorpath") > 0)
        showExceptionMsgDialog(data);
}

function validContent(data) {
    if (data.indexOf("<") >= 0) {
        alert('内容中不要带有“<”号，请修改内容。');
        return false;
    }
    if (data.indexOf("&#") >= 0) {
        alert('内容中不要连续带有这两个符号“&#”，请修改内容。');
        return false;
    }
    return true;
}

function showExceptionMsgDialog(content, title) {
    try { $("#dialogExceptionMessage").remove(); } catch (e) { }
    title = title ? title : "消息";
    //$("<div id='dialogExceptionMessage' title='" + title + "'>" + content + "</div>").dialog({
    $("<div>").attr("id", "dialogExceptionMessage").attr("title", title).html(content).dialog({
        closeText: '关闭',
        width: 850,
        maxHeight: $(window).height() * 0.9,
        show: { effect: "fade", duration: 300 },
        hide: { effect: "fade", duration: 300 },
        buttons: {
            关闭: function () {
                $(this).dialog("close");
            }
        }
    });
}

function showPrompt(content, title) {
    try{
        $("#divPrompt").remove();
    }catch(e){}
    
    if (!content) return;
    
    var selectedEffect = "blind";

    var options = {};
    if ( selectedEffect === "scale" ) {
        options = { percent: 100 };
    } else if ( selectedEffect === "size" ) {
        options = { to: { width: 280, height: 185 } };
    }
    
    $('body').append($( '<div id="divPrompt" class="ui-widget-content ui-corner-all" style="z-index:999; font-size: 14px; color:#FFF; background:#72B400; top:1px; left:400px; padding: 1em; POSITION:fixed;_position:absolute; display: block;">'
        + (title ? '<h3 class="ui-widget-header ui-corner-all">' + title + '</h3>' : '')
        + content + '</div>' ));
    $('#divPrompt').show( selectedEffect, options, 500, function(){
        setTimeout(function() {
            $( "#divPrompt:visible" ).fadeOut();
        }, 3000 );
    } );
};

function getCurrentUrl(containParam){
    var cururl = window.location.href;
    cururl = cururl.replace(window.location.hash, "").replace("#", "");
    if (window.location.search)
        cururl += "&";
    else
        cururl += "?";
    return cururl + "p_rdm=" + Math.random() + "&";
}

function gotoAuthorize(appkey, redirectUri, promptMsg, callback, callbackParams) {
    try {
        $("#divGotoAuthorize").remove();
    } catch (e) { }

    if (!promptMsg)
        promptMsg = "<p>为了确保您数据安全，程序需要您的授权以验证您店铺所有人身份！</p>";

    $("<div>").attr("id", "divGotoAuthorize")
	.html("<div style='text-align:center;'>" + promptMsg + "<p><a target='_blank' id='aGotoAuthorize' class='aBtn' href='https://oauth.taobao.com/authorize?response_type=code&client_id=" + appkey + "&redirect_uri=" + redirectUri + "&view=web&state=RedirectUrl%3Ajs_close'>前往授权</a></p></div>")
    .dialog({
        dialogClass: "untitle",
        width: 300,
        maxHeight: $(window).height() * 0.98,
        modal: true,
        show: { effect: "fade", duration: 300 },
        hide: { effect: "fade", duration: 300 },
        buttons: {
            关闭: function () {
                $(this).dialog("close");
                $(this).dialog("destroy");
            }
        }
    });

    $("#aGotoAuthorize").button().click(function () {
        $("#divGotoAuthorize").dialog("close");
        $("#divGotoAuthorize").dialog("destroy");

        try {
            $("#divAuthorizeResult").remove();
        } catch (e) { }

        $("<div>").attr("id", "divAuthorizeResult")
		.html("是否已授权成功？")
        .dialog({
            dialogClass: "untitle",
            width: 300,
            maxHeight: $(window).height() * 0.98,
            modal: true,
            show: { effect: "fade", duration: 300 },
            hide: { effect: "fade", duration: 300 },
            buttons: {
                是: function () {
                    $(this).dialog("close");
                    $(this).dialog("destroy");

                    if (callback) {
                        if (callbackParams)
                            callback(callbackParams);
                        else
                            callback();
                    }
                },
                否: function () {
                    $(this).dialog("close");
                    $(this).dialog("destroy");
                }
            }
        });
    });
}

function openNewWindowWithoutRefer(full_link) {
    if (/MSIE (\d+\.\d+);/.test(navigator.userAgent) || /MSIE(\d+\.\d+);/.test(navigator.userAgent)) {
        var referLink = document.createElement('a');
        referLink.href = "javascript:;";
        referLink.onclick = function () {
            window.open(full_link);
        }
        //referLink.target = "_blank";
        document.body.appendChild(referLink);
        referLink.click();
    }
    else {
        window.open('javascript:window.name;', '<script>location.replace("' + full_link + '")<\/script>');
    }
}
function addFavorite(url, title) {
    if (document.all && window.external) {
        window.external.addFavorite(url, title);
    }
    else if (window.sidebar && window.sidebar.addPanel) {
        window.sidebar.addPanel(title, url, "");
    }
    else if (window.sidebar && window.sidebar.addFavorite) {
        window.sidebar.addFavorite(title, url, "");
    }
}
$(function () {
    $("a").each(function () {
        var link = $(this).attr("href");
        if (!link || !link.indexOf)
            return;
        if (link.indexOf("pan.baidu.com") >= 0 || link.indexOf("yun.baidu.com") >= 0) {
            var target = $(this).attr("target");
            $(this).attr("href", "javascript:;");
            $(this).attr("target", "_self");
            $(this).click(function () {
                openNewWindowWithoutRefer(link);
                //if ("_self" == target)
                //{ }
                //else if ("_blank" == target) {
                //}
            })
        }
    });
});