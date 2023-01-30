package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBind {

    public AddPlace addPlacePayLoad(String Name, String Address)
    {
        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress(Address);
        p.setLanguage("english");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(Name);
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}


