<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <spring:url var="css" value="/css/"></spring:url>
    <spring:url var="js" value="/js/"></spring:url>
     <spring:url var="img" value="/images/"></spring:url>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Boot - ${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">
    

    <!-- Custom styles for this template -->
    <link href="${css}/myapp.css" rel="stylesheet">
    
    <script type="text/javascript">
    
    window.menu='${title}';
    </script>
     

  </head>

  <body>
  
  <div class="wrapper">
    <!-- Navigation -->
    
   <%@ include file="./shared/nav.jsp" %>
   
   
	<div class="content">
    <!-- Page Content -->
    <!-- This is Home page Content -->
    <c:if test="${userClicksHome == true }">
     <%@ include file="page.jsp" %> 
   
  
    </c:if>
    
     <!-- This is About Us  page Content -->
    <c:if test="${userClicksAbout == true }">
      <%@ include file="about.jsp" %> 
    
     
    
        </c:if>
    
     <!-- This is Services page Content -->
    <c:if test="${userClicksServices == true }">
     <%@ include file="services.jsp" %>
    </c:if>
    
     <!-- This is Contact Us page Content -->
    <c:if test="${userClicksContact== true }">
     <%@ include file="contact.jsp" %>
    </c:if>
    
   <!--  accessDenied -->
    <c:if test="${accessDenied== true }">
     <%@ include file="403.jsp" %>
    </c:if>
   
    </div>
    
    
    
    
    
    
    <!-- /.container -->

    <!-- Footer -->
   <%@ include file="./shared/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="${js}/jquery.min.js"></script>
    <script type="text/javascript" src="${js}/myapp.js"></script>
    <script src="${js}/bootstrap.bundle.min.js"></script>
    
</div>

  </body>

</html>
    