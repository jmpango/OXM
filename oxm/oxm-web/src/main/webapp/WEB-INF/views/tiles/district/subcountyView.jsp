<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<%@ taglib prefix="oxmTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="oxmBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks"%>
<%@ taglib prefix="oxmDistrictBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks/districts"%>

<div style="margin: 5px; width: 100%;">
	<oxmBreadcrambs:cpanel startingBreadcramb="true" />
	<oxmDistrictBreadcrambs:districts />
	<oxmDistrictBreadcrambs:subcounty district="${district }" />
	Sub Counties
</div>

<div id="buttonStrip">
	<div class="contextual">
		<a id="lnkAddSubCounty" class="uiButton"
			href="${baseUrl }/subcounty/add/${district.id }">Add</a> <a
			id="lnkEditSubCounty" class="uiButton"
			href="${baseUrl }/subcounty/edit/">Edit</a> <a
			id="lnkDeleteSubCounty" class="uiButton"
			href="${baseUrl }/subcounty/delete/${district.id }/">Delete</a> <a
			id="lnkSubCountyParishes" class="uiButton"
			href="${baseUrl }/parish/view/">Parishes</a>
	</div>
	<div style="clear: both"></div>
</div>

<div>

	<oxmTags:name-of-item-on-page name="SubCounty" />

	<table class="recordTable list" width="100%" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox" name="cbxSelectAllItems"
					id="cbxSelectAllItems" />
				</th>
				<th>No.</th>
				<th>Name</th>
				<th>Parishes</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty subcounties  && fn:length(subcounties) > 0}">
				<c:forEach var="subcounty" items="${subcounties }"
					varStatus="status">
					<tr id="${subcounty.id }">
						<td><oxmTags:rowcheckbox
								nameOfItemOnPage="${nameOfItemOnPage}" id="${subcounty.id }" />
						</td>
						<td>${status.count }</td>
						<td>${subcounty.name }</td>
						<td>${subcounty.parishesString }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>