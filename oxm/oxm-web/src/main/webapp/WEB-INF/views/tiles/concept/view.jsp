<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sysTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="oxmConceptBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks/concept"%>
<%@ taglib prefix="oxmBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks"%>

<div style="margin: 5px; width: 100%;">
	<oxmBreadcrambs:cpanel startingBreadcramb="true" />
	<oxmConceptBreadcrambs:concepts />
</div>

<jsp:include page="/WEB-INF/views/tiles/concept/searchfields.jsp"></jsp:include>

<div id="buttonStrip">
	<div class="contextual" style="float: left;">
		<a id="lnkAddConcept" class="uiButton" href="${baseUrl }/concept/add/">Add</a>
		<a id="lnkEditConcept" class="uiButton"
			href="${baseUrl }/concept/edit/">Edit</a> <a id="lnkDeleteConcept"
			class="uiButton" href="${baseUrl }/concept/delete/">Delete</a>
	</div>
	<div style="float: right;">
		<%@ include file="/WEB-INF/views/navigation.jsp"%>
	</div>
	<div style="clear: both"></div>
</div>
<div>

	<sysTags:name-of-item-on-page name="Concept" />

	<table class="recordTable">
		<thead>
			<tr>
				<th><input type="checkbox" name="cbxSelectAllItems"
					id="cbxSelectAllItems" /></th>
				<th>Name</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty concepts  && fn:length(concepts) > 0}">
					<c:forEach var="concept" items="${concepts }">
						<tr id="${concept.id }">
							<td><sysTags:rowcheckbox
									nameOfItemOnPage="${nameOfItemOnPage}" id="${concept.id }" />
							</td>
							<td>${concept.name }</td>
							<td>${concept.description }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
</div>