"use strict"
$.prototype.nullChecker =x=>{
	let flag = false
	let i = 0
	for (i in x){
		if(x[i] === ''){
			flag = true
		}
	}
	return flag
}
function CheckExtension (x) {
	let flag = false
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
	let maxSize = 5242880;
	if(x.fsize >= maxSize) {
		alert('파일사이즈 초과')
		flag = true
	}
	if(regex.test(x.fname)){
		alert('해당 종류의 파일은 업로드 불가')
		flag = true
	}
	return flag
}