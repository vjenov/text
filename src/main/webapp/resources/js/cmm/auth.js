"use strict";
var auth = auth || {}
auth = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.'
    let _, js, css, img, auth_vue_js, brd_js, router_js, cookie_js, adm_js
    let init =()=>{
        _ = $.ctx()
        js = $.js()
        css = $.css()
        img = $.img()
        auth_vue_js = js+'/vue/auth_vue.js'
        brd_js = js+'/brd/brd.js'
        router_js = js + '/cmm/router.js'
        cookie_js = js + '/cmm/cookie.js'
        adm_js = js + '/adm/adm.js'
    }
    let onCreate =()=>{
        init()
        $.when(
			$.getScript(auth_vue_js),
			$.getScript(router_js),
			$.getScript(brd_js),
			$.getScript(cookie_js),
			$.getScript(adm_js)
		)		
        .done(()=>{
        	setContentView()
    		$('#a_go_join').click(e=>{
         		e.preventDefault()
         		$('head').html(auth_vue.join_head())
		        $('body').html(auth_vue.join_body())
		        $('#uid').keyup(()=>{
		        	if($('#uid').val().length > 2){
		        		$.ajax({
		        			url : _+'/users/'+$('#uid').val()+'/existId',
		        	    	contentType : 'application/json',
		        	    	success : d => {
		        	    		alert('AJAX 성공 : '+d.msg)
		        	    		if(d.msg === 'Success')
		        	    			$('#dupl_check')
		        	    			.val('사용가능한 ID 입니다')
		        	    			.css('color','blue')
		            			else
		            				$('#dupl_check')
		        	    			.val('이미 사용한 ID 입니다')
		        	    			.css('color','red')
		        	    	},
		        	    	error : e => {
		        	    		alert('AJAX 실패')
		        	    	}
		        		})
		        	}
		        });
		        $('<button>',{
		            text : '회원가입',
		            href : '#',
		            click : e=>{
		            	e.preventDefault()
		            	join()
		            }
		        })
		        .addClass('btn btn-primary btn-lg btn-block')
		        .appendTo('#btn_join')
    		})
        }).fail(()=>{alert(WHEN_ERR)})
    }
    let setContentView =()=>{
    	$('head').html(auth_vue.login_head({css: $.css(), img: $.img()}))
        $('body').addClass('text-center')
        .html(auth_vue.login_body({css: $.css(), img: $.img()}))
        login()
        access()
    }
    let join =()=>{
    	let data = {uid : $('#uid').val(),
    			pwd : $('#pwd').val(),
    			uname : $('#uname').val()}
    	alert('전송아이디: '+data.uid)
        $.ajax({
	    	url : _+'/users/',
	    	type : 'POST',
	    	dataType : 'json',
	    	data : JSON.stringify(data),
	    	contentType : 'application/json',
	    	success : d => {
	    		alert('AJAX 성공 : '+d.msg)
	    		if(d.msg === 'Success'){
	    			$('head').html(auth_vue.login_head({css: $.css(), img: $.img()}))
	    	        $('body').addClass('text-center')
	    	        .html(auth_vue.login_body({css: $.css(), img: $.img()}))
	    			login()
	    		}else
    				alert('회원가입 실패')
	    		
	    	},
	    	error : e => {
	    		alert('AJAX 실패')
	    	}
    	})
    }
    let login =()=>{
        $('<button>',{
        	text : "로그인",
        	click : e => {
        		e.preventDefault()
        		$.ajax({
        			url: _+'/users/'+$('#uid').val(),
        			type: 'POST',
        			data: JSON.stringify({
      				  uid : $('#uid').val(),
    				  pwd : $('#pwd').val()
    				}),
        			dataType: 'json',
        			contentType: 'application/json',
        			success: d =>{
        				alert('로그인성공'+router_js)
//        				$.extend(new User(d))
        				setCookie("UID", d.uid)
        				alert('저장된 쿠키:::' + getCookie("UID"))
        				brd.onCreate()
        			},
        			error: e =>{
        				alert('AJAX ERROR ')
        			}
        			
        		})
        	}
        })
        .addClass("btn btn-lg btn-primary btn-block")
        .appendTo('#btn_login')
    }
    let access =()=>{
    	$('#a_go_admin').click(()=>{
				adm.onCreate()
        	/*	let ok = confirm('사원입니까')
        	if(ok){
        	 * let aid = prompt('사원번호를 입력하시오')
        		$.ajax({
        			url: _+'/admins/'+aid,
        			type: 'POST',
        			data: JSON.stringify({
        				aid : aid,
        				pwd : prompt('비밀번호를 입력하시오')
        			}),
        			dataType: 'json',
        			contentType: 'application/json',
        			success: d=>{
        				if(d.msg==='Success'){
        					alert('welcome')
        					$('body').html('<h1>aa</h1>')
        					adm.onCreate()
        				}else(
        					alert('접근권한이 없습니다.'))
        					app.run(_)
        			},
        			error : function(request,status,error){
        		        alert("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        		       }
        		})
        		}*/
    	})
    }
    return {onCreate, join, login}
})();