<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="회원정보 수정"/>
<%@ include file="../common/head.jspf"  %>   


<script>
   //회원정보수정
   let MemberModify__submitDone = false;
   function MembeModify__submit(form){
      if ( MembeModify__submitDone ) {
    	  alert('처리중입니다..');
         return;
      }
      //좌우공백 제거
      form.loginPw.value = form.loginPw.value.trim();
      
      if (form.loginPw.value.length > 0 ){
         form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
         
         if(form.loginPwConfirm.value.length == 0){
        	 alert('비밀번호확인을 입력해주세요.');
        	 form.loginPwConfirm.focus();
        	 return;
         }
         if(form.loginPw.value != form.loginPwConfirm.value ){
        	 alert('비밀번호가 일치하지 않습니다.');
        	 form.loginPwConfirm.focus();
        	 return;
         }
      }
      // 이름
      form.name.value = form.name.value.trim();
      
      if(form.name.value.length == 0){
     	 alert('이름을 입력해주세요.');
     	 form.name.focus();
     	 return;
      }
      
      // 닉네임
      form.nickname.value = form.nickname.value.trim();
      
      if(form.nickname.value.length == 0){
     	 alert('닉네임을 입력해주세요.');
     	 form.nickname.focus();
     	 return;
      }
      
      //이메일
	  form.email.value = form.email.value.trim();
      
      if(form.email.value.length == 0){
     	 alert('이메일을 입력해주세요.');
     	 form.email.focus();
     	 return;
      }
      
      //휴대전화번호
      form.cellphoneNo.value = form.cellphoneNo.value.trim();
      
      if(form.cellphoneNo.value.length == 0){
     	 alert('휴대전화번호를 입력해주세요.');
     	 form.cellphoneNo.focus();
     	 return;
      }
      
      
      MembeModify__submitDone = true;
      form.submit();
   }
</script>
   
<section class="mt-5">
      <div class="container mx-auto px-3">
            <form class="table-box-type-1" method="post" action="../member/doModify" onsubmit="MemberModify__submit(this); return false;">
                  <input type="hidden" name="memberModifyAuthKey" value="${param.memberModifyAuthKey }" />

                  <table>
                        <colgroup>
                              <col width="200" />
                        </colgroup>
                        <tbody>
                              <tr>
                                  <th>로그인아이디</th>
                                  <td>${rq.loginedMember.loginId}</td>
                              </tr>
                              <tr>
                                  <th>새 비밀번호</th>
                                  <td>
                                      <input type="password" class="input input-bordered" name="loginPw" placeholder="새 비밀번호를 입력해주세요." />
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
                                      <input type="text" class="input input-bordered" name="name" placeholder="이름을 입력해주세요."
                                                value="${rq.loginedMember.name }" />
                                  </td>
                              </tr>
                              <tr>
                                  <th>닉네임</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="nickname" placeholder="닉네임을 입력해주세요."
                                                value="${rq.loginedMember.nickname }" />
                                  </td>
                              </tr>
                              <tr>
                                  <th>이메일</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="email" placeholder="이메일을 입력해주세요."
                                              value="${rq.loginedMember.email }" />
                                  </td>
                              </tr>
                              <tr>
                                  <th>휴대전화번호</th>
                                  <td>
                                      <input type="text" class="input input-bordered" name="cellphoneNo" placeholder="휴대전화번호를 입력해주세요."
                                                value="${rq.loginedMember.cellphoneNo }" />
                                  </td>
                              </tr>

                              <tr>
                                  <th>회원정보수정</th>
                                  <td>
                                      <input type="submit" class="btn btn-primary" value="회원정보수정" />
                                      <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
                                  </td>
                              </tr>
                        </tbody>
                  </table>
            </form>
      </div>
</section>

   
   
<%@ include file="../common/foot.jspf"  %>   