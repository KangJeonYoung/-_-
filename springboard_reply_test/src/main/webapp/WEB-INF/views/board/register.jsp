<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>    
    
<script type="text/javascript" src="../resources/ckeditor/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'ckeditor', {
    } );
</script>
<div class="row">
	<div class="col-lg-12">
		<h1 class = "page-header">Board Register</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class ="panel panel-default">
		
			<div class="panel-heading">Board Register</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
			
				<form role="form" action="/board/register" method="post">
					<div class="form-group">
						<label>제목</label> <input class="form-control" name='title'>
					</div>
					
					<div class ="form-group">
						<label>텍스트 내용</label>
						<textarea class = "ckeditor" rows="3" name ='content'></textarea>
						</div>
						
						<div class = "form-group">
							<label>작성자</label> <input class = "form-control" name='writer' value="${userid }" readonly="readonly">
						</div>
						<button type="submit" class = "btn btn-default">글 쓰 기</button>
						<button type="reset" class = "btn btn-default">리 셋</button>
				</form>
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>