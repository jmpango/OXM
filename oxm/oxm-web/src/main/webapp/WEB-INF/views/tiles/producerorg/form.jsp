<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
form#concept input[type="text"] {
	width: 70%;
}

#conceptcategorylist span {
	display: inline-block;
	width: 50%;
}
</style>
<div>
	<div style="margin: 5px; width: 100%;">
		<label class="uiLabel">Production Organization >> </label><a
			title="Back to Production Organization"
			href="${baseUrl }/pOrganization/view/">Back</a>
	</div>
	<form:form action="${baseUrl }/pOrganization/save/"
		commandName="pOrganization">

		<form:hidden path="id" />
		<div class="splitcontentleft">
			<div class="box tabular">
				<table>
					<tr>
						<td><label>Name <span class="required">*</span> </label>
						</td>
						<td><form:input path="name" cssClass="uiTextbox" />
						</td>
					</tr>
					<tr>
						<td><label>District <span class="required">*</span> </label>
						</td>
						<td><form:select id="dddistrict" path="select"
								cssClass="uiTextbox" />
						</td>
					</tr>
					<tr>
						<td><label>Sub-County <span class="required">*</span> </label>
						</td>
						<td><form:select id="ddsubcounty" path="select"
								cssClass="uiTextbox" />
						</td>
					</tr>


				</table>
			</div>
		</div>
		<div style="clear: both"></div>
		<div>
			<input id="btnSavePOrganization" type="submit" value="Save"
				class="uiButton" />
		</div>
	</form:form>
</div>