<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="oxmTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="oxmBreadcrambs"
	tagdir="/WEB-INF/tags/breadcramblinks"%>

<div style="margin: 5px; width: 100%;">
	<oxmBreadcrambs:cpanel startingBreadcramb="true" />
	<oxmBreadcrambs:crops />
</div>

<div id="buttonStrip">
	<div class="contextual" style="float: left;">
		<a id="lnkAddCrop" class="uiButton" href="${baseUrl }/crop/add/">Add</a>
		<a id="lnkEditCrop" class="uiButton" href="${baseUrl }/crop/edit/">Edit</a>
		<a id="lnkDeleteCrop" class="uiButton" href="${baseUrl }/crop/delete/">Delete</a>
		&emsp;<a id="lnkCropDetails" class="uiButton"
			href="${baseUrl }/crop/details/">Details</a>
	</div>
	<div style="float: right;">
		<%@ include file="/WEB-INF/views/navigation.jsp"%>
	</div>
	<div style="clear: both"></div>
</div>
<div>

	<oxmTags:name-of-item-on-page name="Crop" />

	<table class="recordTable" width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox" name="cbxSelectAllItems"
					id="cbxSelectAllItems" /></th>
				<th>Name</th>
				<th>Inputs</th>
				<th>seed Varieties</th>
				<th>ploughing Methods</th>
				<th>inter-Croping Types</th>
				<th>units Of Measure</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty crops  && fn:length(crops) > 0}">
					<c:forEach var="crop" items="${crops }">
						<tr id="${crop.id }">
							<td><oxmTags:rowcheckbox
									nameOfItemOnPage="${nameOfItemOnPage}" id="${crop.id }" /></td>
							<td>${crop.name }</td>
							<td>${fn:length(crop.inputs) }</td>
							<td>${fn:length(crop.seedVarieties) }</td>
							<td>${fn:length(crop.ploughingMethods) }</td>
							<td>${fn:length(crop.interCroppingTypes) }</td>
							<td>${crop.unitsOfMeasureNames}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
</div>