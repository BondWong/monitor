/**
 * 
 */

angular.module("app").filter("search", function() {
	return search;
}).filter("friendly", function() {
	return function(input) {
		var now = moment();
		var date = moment(input);

		var minDifference = now.diff(date, "minutes");
		minDifference = minDifference == 0 ? 1 : minDifference;
		var hourDifference = now.diff(date, "hours")
		var dayDifference = now.diff(date, "days")

		if (minDifference < 60 && minDifference > 0)
			return minDifference + "mins ago";
		else if (hourDifference < 24)
			return hourDifference + "hours ago";
		else if (dayDifference >= 1 && dayDifference <= 3)
			return dayDifference + "days ago";
		else
			return moment(input).format("YYYY-M-D");
	}
});

function search(input, name) {
	var output = [];
	angular.forEach(input, function(element, index) {
		if (name == undefined || name == "" || name == null) {
			output = input;
			return;
		}
		if (element.name.indexOf(name) != -1)
			output.push(element);
	});

	return output;
}