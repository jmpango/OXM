<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<%@ taglib prefix="oxmTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="oxmBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks"%>
<%@ taglib prefix="oxmProducerOrgBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks/producerorg"%>

<div style="margin: 5px; width: 100%;">
	<oxmBreadcrambs:cpanel startingBreadcramb="true" />
	<oxmProducerOrgBreadcrambs:producerorgs />
	<oxmProducerOrgBreadcrambs:details producerOrg="${pOrg }" />
	<oxmProducerOrgBreadcrambs:committees producerOrg="${pOrg }" />
</div>

<div id="buttonStrip">
	<div class="contextual" style="float: left;">
		<a id="lnkAddPOrgCommitte" class="uiButton"
			href="${baseUrl }/porg/committee/add/${pOrg.id }">Add</a> <a
			id="lnkEditPOrgCommitte" class="uiButton"
			href="${baseUrl }/porg/committee/edit/">Edit</a> <a
			id="lnkDeletePOrgCommitte" class="uiButton"
			href="${baseUrl }/porg/committee/delete/">Delete</a>


	</div>
	<div style="float: right;">
		<%@ include file="/WEB-INF/views/navigation.jsp"%>
	</div>
	<div style="clear: both"></div>
</div>
<div>

	<oxmTags:name-of-item-on-page name="Committee" />

	<table class="recordTable" width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox" name="cbxSelectAllItems"
					id="cbxSelectAllItems" />
				</th>
				<th>No</th>
				<th>Committee</th>
				<th>No. of Members</th>
				<th>Producer organisation</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when
					test="${not empty pOrg.committees  && fn:length(pOrg.committees) > 0}">
					<c:forEach var="committee" items="${pOrg.committees }"
						varStatus="status">
						<tr id="${committee.id }">
							<td><input type="checkbox"
								name="selected${nameOfItemOnPage}" value="${committee.id }" />
							</td>
							<td>${status }</td>
							<td>${committee.name }</td>
							<td>${fn:length(committee.members) }</td>
							<td>${committee.producerOrg.name }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
</div>