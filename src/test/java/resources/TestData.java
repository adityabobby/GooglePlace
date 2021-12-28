package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public static AddPlace addPlaceData(String name, String accuracy, String language) {
		Location l = new Location();
		l.setLat("-38.383494");
		l.setLng("33.427362");
		AddPlace p = new AddPlace();
		p.setAccuracy(accuracy);
		p.setAddress("31, side layout, cohen 09");
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLocation(l);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		return p;
	}

}
