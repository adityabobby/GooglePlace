package resources;

public enum PlaceValidations {

	AddPlaceAPI("/maps/api/place/add/json"), GetPlaceAPI("/maps/api/place/get/json");

	private String resource;

	PlaceValidations(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
