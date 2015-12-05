/**
 * 
 */

"use strict";

angular.module("app").filter("friendly", function() {
	return function(input) {
		var now = moment();
		var date = moment(input);

		var minDifference = now.diff(date, "minutes");
		minDifference = minDifference == 0 ? 1 : minDifference;
		var hourDifference = now.diff(date, "hours")
		var dayDifference = now.diff(date, "days")

		if (minDifference < 60 && minDifference > 0)
			return minDifference + "分钟前";
		else if (hourDifference < 24)
			return hourDifference + "小时前";
		else if (dayDifference >= 1 && dayDifference <= 3)
			return dayDifference + "天前";
		else
			return moment(input).format("YYYY-M-D");
	}
}).filter(
		"isImage",
		function() {
			return function(input) {
				return input == "image/jpeg" || input == "image/gif"
						|| input == "image/png";
			}
		}).filter(
		"isVideo",
		function() {
			return function(input) {
				return input == "video/x-flv" || input == "video/mp4"
						|| input == "video/quicktime"
						|| input == "video/x-msvideo"
						|| input == "video/x-ms-wmv";
			}
		}).filter("trustUrl", [ "$sce", function($sce) {
	return function(input) {
		return $sce.trustAsResourceUrl(input);
	};
} ]);
