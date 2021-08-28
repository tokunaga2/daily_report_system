<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
   <h3>フォローした従業員の日報一覧</h3>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                 <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

 <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follow/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>





         <h3>フォローしている従業員</h3>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
            <c:if test="${sessionScope.login_employee.admin_flag == 1}">         <th>操作</th></c:if>
                </tr>
                <c:forEach var="employee" items="${followees}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${employee.code}" /></td>
                        <td><c:out value="${employee.name}" /></td>

    <c:if test="${sessionScope.login_employee.admin_flag == 1}">

                        <td>
                            <c:choose>
                                <c:when test="${employee.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/employees/show?id=${employee.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


         <h3>あなたをフォローしている従業員</h3>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
            <c:if test="${sessionScope.login_employee.admin_flag == 1}">         <th>操作</th></c:if>
                </tr>
                <c:forEach var="employee" items="${followers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${employee.code}" /></td>
                        <td><c:out value="${employee.name}" /></td>

    <c:if test="${sessionScope.login_employee.admin_flag == 1}">

                        <td>
                            <c:choose>
                                <c:when test="${employee.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/employees/show?id=${employee.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



        <p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>