<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form>
	<div class="form-group" ng-show="client.lastVisit">
		<label for="clientLastVisit">Client Last Visit</label>
		<p id="clientLastVisit">{{client.lastVisit}}</p>
	</div>
	<div class="form-group">
		<label for="clientPhone">Client Phone</label> <input type="text"
			class="form-control" id="clientPhone" ng-model="client.phone" /> <label
			for="addressStreet">Street</label> <input type="text"
			class="form-control" id="addressStreet"
			ng-model="client.address.street" />
		<div class="inline-form">
			<label for="addressCity">City</label> <input type="text"
				class="form-control" id="addressCity" ng-model="client.address.city" />
			<label for="addressState">State</label> <input type="text"
				class="form-control" id="addressState"
				ng-model="client.address.state" /> <label for="addressZip">Zip</label>
			<input type="text" class="form-control" id="addressZip"
				ng-model="client.address.zip" /> <label for="addressCountry">Country</label>
			<input type="text" class="form-control" id="addressCountry"
				ng-model="client.address.country" />
		</div>
	</div>
	<button type="button" class="btn btn-default" ng-click="updateClient()">Update</button>
</form>