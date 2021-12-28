Feature: Validation of Add Place API

@AddPlace
Scenario Outline:: To post the http request
	Given Add place payload for "POST" with "<name>" "<accuracy>" "<language>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API call is success with status code "200"
	And "status" is "OK"
	And "scope" is "APP"
	
Examples:
	|name|accuracy|language|
	|Frontline house|60|English|

@GetPlace	
Scenario: To get the http request
	Given Add place payload for "GET"
	When user calls "GetPlaceAPI" with "Get" http request
	Then the API call is success with status code "200"
	And I validate the address is matching with post request
	