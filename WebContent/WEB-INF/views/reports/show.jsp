<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p><a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a></p>
                </c:if>

                <c:if test="${sessionScope.login_employee.id != report.employee.id}">
                    <p><form method="POST" action="<c:url value='/FollowServlet' />">

                    <input type="hidden" name="_token" value="${_token}" />


<button type="submit">

<c:choose>
    <c:when test="${sessionScope.follow_count > 0}">
        フォローを解除する
    </c:when>
    <c:otherwise>
        従業員をフォローする
    </c:otherwise>
</c:choose>



</button>

                    </form>
                </c:if>



                <p><form method="POST" action="<c:url value='/GoodServlet' />">

                    <input type="hidden" name="_token" value="${_token}" />


<button type="submit">

<c:choose>
    <c:when test="${sessionScope.good_count > 0}">
        いいね解除
    </c:when>
    <c:otherwise>
        いいね！
    </c:otherwise>
</c:choose>



</button>

                    </form>





                    <h3>いいねしている従業員</h3>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
            <c:if test="${sessionScope.login_employee.admin_flag == 1}">         <th>操作</th></c:if>
                </tr>
                <c:forEach var="employee" items="${employees}" varStatus="status">
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





            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
        <c:out value="${test}" />
    </c:param>
</c:import>