<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="회원가입"/>
<%@ include file="../common/head.jspf"  %>   

<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>

<script>
   let validLoginId = "";
   
   function MemberJoin_submit(form){
   let MemberJoin_submitDone=false;
      if(MemberJoin_submitDone){
         alert('처리중입니다..');
         return;
      }
      
      form.loginId.value = form.loginId.value.trim();
      
      if(form.loginId.value.length == 0){
         alert('로그인아이디를 입력해주세요.');
         form.loginId.focus();
         return;
      }
      
      if(form.loginId.value.length != validLoginId){
         alert('해당 로그인 아이디는 올바르지 않습니다. 다른로그인 아이디를 입력해주세요.');
         form.loginId.focus();
         return;
      }
      
      form.loginPw.value = form.loginPw.value.trim();
      
      if(form.loginPw.value.length == 0){
         alert('로그인비밀번호를 입력해주세요.');
         form.loginPw.focus();
         return;
      }
      
      form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
      
      if(form.loginPwConfirm.value.length == 0){
         alert('로그인비밀번호 확인');
         form.loginPwConfirm.focus();
         return;
      }
      
      if(form.loginPwConfirm.value != form.loginPw.value){
         alert('비밀번호가 일치하지 않습니다.');         
         form.loginPw.value='';
         form.loginPwConfirm.value='';
         form.loginPw.focus();
         return;
      }
      
      form.name.value = form.name.value.trim();
      
      if(form.name.value.length == 0){
         alert('이름을 입력해주세요.');
         form.name.focus();
         return;
      }
      
      form.nickname.value = form.nickname.value.trim();
      
      if(form.nickname.value.length == 0){
         alert('닉네임을 입력해주세요.');
         form.nickname.focus();
         return;
      }
      
      form.email.value = form.email.value.trim();
      
      if(form.email.value.length == 0){
         alert('이메일을 입력해주세요.');
         form.email.focus();
         return;
      }
      form.cellphoneNo.value = form.cellphoneNo.value.trim();
      
      if(form.cellphoneNo.value.length == 0){
         alert('휴대전화번호을 입력해주세요.');
         form.cellphoneNo.focus();
         return;
      }
      
      MemberJoin_submitDone = true;
      form.submit();
   }
   
   function checkLoginIdDup(el){
      const form = $(el).closest('form').get(0);
      
      if(form.loginId.value.length == 0){
         validLoginId = '';
         return;
      }
      
      if(validLoginId == form.loginId.value){
         return;
      }
      
      $('.loginId-msg').html('<div class="mt-2">체크중...</div>');
      
      $.get("../member/getLoginIdDup", {
         isAjax : 'Y',
         loginId : form.loginId.value
      }, function(data){
         $('.loginId-msg').html('<div class="mt-2">'+data.msg+'</div>');
         if(data.success){
            validLoginId = data.data1;
         }else{
            validLoginId = '';
         }
      }, 'json');
   }
   
   const checkLoginIdDupDebounced = _.debounce(checkLoginIdDup, 500);
   
</script>

	   
<section class="mt-5">
      <div class="container mx-auto px-3">
            <form class="table-box-type-1" method="post" action="../member/doJoin" onsubmit="MemberJoin__submit(this); return false;">
                  <input type="hidden" name="afterJoinUri" value="${param.afterJoinUri }" />

                  <table>
                        <colgroup>
                              <col width="200" />
                        </colgroup>
                        <tbody>
                              <tr>
                                  <th>로그인아이디</th>
                                  <td>
                                  	<input type="text" class="input input-bordered" name="loginId" placeholder="로그인아이디를 입력해주세요." onkeyup="checkLoginIdDupDebounced(this);" autocomplete="off" />
                                  	<div class="loginId-msg"></div>
                                  </td>
                              </tr>
                              <tr>
                                  <th>새 비밀번호</th>
                                  <td>
                                      <input type="password" class="input input-bordered" name="loginPw" placeholder="비밀번호를 입력해주세요." />
                                  </td>
                              </tr>
                              <tr>
                                  <th>새 비밀번호 확인</th>
                                  <td>
                                      <input type="password" class="input input-bordered" name="loginPwConfirm" placeholder="새 비밀번호 확인을 입력해주세요." />
                                  </td>
                              </tr>
                              <tr>
                                  <th>이름</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="name" placeholder="이름을 입력해주세요." />
                                  </td>
                              </tr>
                              <tr>
                                  <th>닉네임</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="nickname" placeholder="닉네임을 입력해주세요." />
                                  </td>
                              </tr>
                              <tr>
                                  <th>이메일</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="email" placeholder="이메일을 입력해주세요."/>
                                  </td>
                              </tr>
                              <tr>
                                  <th>휴대전화번호</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="cellphoneNo" placeholder="휴대전화번호를 입력해주세요."/>
                                  </td>
                              </tr>

                              <tr>
                                  <th>회원가입</th>
                                    <td>
                                          <input type="submit" class="btn btn-primary" value="회원가입" />
                                          <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
                                    </td>
                              </tr>
                        </tbody>
                  </table>
            </form>
      </div>
</section>

   
   
<%@ include file="../common/foot.jspf"  %>   