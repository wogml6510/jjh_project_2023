<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="비밀번호 확인" />
<%@ include file="../common/head.jspf"%>

<script>
   //회원정보수정
      let MemberCheckPassword_submitDone=false;
   function MemberCheckPassword_submit(form){
      if(MemberCheckPassword_submitDone){
         return;
      }

      form.loginPwInput.value = form.loginPwInput.value.trim();
      
      if(form.loginPwInput.value.length == 0){
         alert('비밀번호를 입력해주세요.');
         form.loginPwInput.focus();
         return;
      }

      if(form.loginPwInput.value != form.loginPw.value){
          alert('비밀번호가 일치하지 않습니다.11111');
         form.loginPwInput.value = '';
          form.loginPwInput.focus();
          return;
      }
      
      form.loginPw.value = form.loginPwInput.value;
      form.loginPwInput.value = '';
      
      MemberCheckPassword_submitDone = true;
      form.submit();
   }
</script>

<section class="mt-5">
      <div class="container mx-auto">
            <form class="table-box-type-1" method="post" action="../member/doCheckPassword"  onsubmit="MemberCheckPassword_submit(this); return false;">
            <input type="hidden" name="replaceUri" value="${param.replaceUri}"/>
            <input type="hidden" name="loginPw" value="${rq.loginedMember.loginPw }"/>
               <table>
                  <colgroup>
                     <col width="200"/>
                  </colgroup>
                  
                        <tbody>
                              <tr>
                                 <th>로그인아이디</th>
                                 <td>
                                 	${rq.loginedMember.loginId}
                                 </td>
                              </tr>
                              <tr>
                                 <th>로그인비밀번호</th>
                                 <td>
                                    <input type="password" class="w-96 input input-bordered w-full max-w-xs"  name="loginPwInput" placeholder="로그인비밀번호"/>
                                 </td>
                              </tr>
                              <tr>
                                 <th>비밀번호 확인</th>
                                 <td>
                                    <input type="submit"  class="btn btn-primary" value="비밀번호 확인"/>
                                    <button type="button"  class="btn btn-outline btn-primary"  onclick="history.back();">뒤로가기</button>
                                 </td>
                              </tr>
                        </tbody>
                  </table>
            </form>
      </div>
</section>

<%@ include file="../common/foot.jspf"%>