var matchPattern = function(data, fieldName, pattern){
    var value = Object.byString(JSON.parse(data),fieldName);
    return value ?  new RegExp(pattern).test(value) : false;
};

var checkEmail = function (data, fieldName) {
    var emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return matchPattern(data, fieldName, emailPattern)
};

var checkNumber = function (data, fieldName){
    var pattern = '\\d+';
    return matchPattern(data, fieldName, pattern)
};

var checkRequired = function(data, fieldName){
    var value = Object.byString(JSON.parse(data), fieldName);
    return !!(value && value !== null);
};

Object.byString = function(o, s) {
    s = s.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
    s = s.replace(/^\./, '');           // strip a leading dot
    var a = s.split('.');
    for (var i = 0, n = a.length; i < n; ++i) {
        var k = a[i];
        if (k in o) {
            o = o[k];
        } else {
            return;
        }
    }
    return o;
}