/**
 * 该文件存储一些常见的Jquery扩展
 */

/**
 * 用户上传头像实时预览
 */
jQuery.fn.extend({
    uploadPreview: function(opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            // ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function() {}
        }, opts || {});
        _self.getObjectURL = function(file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function() {
            if (this.value) {
                try {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                } catch (e) {
                    var src = "";
                    var obj = $("#" + opts.Img);
                    var div = obj.parent("div")[0];
                    _self.select();
                    if (top != self) {
                        window.parent.document.body.focus()
                    } else {
                        _self.blur()
                    }
                    src = document.selection.createRange().text;
                    document.selection.empty();
                    obj.hide();
                    obj.parent("div").css({
                        'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                        'width': opts.Width + 'px',
                        'height': opts.Height + 'px'
                    });
                    div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                }
                opts.Callback()
            }
        })
    }
});