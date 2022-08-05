package za.co.sturtium.sturtiuminformatics;

public class WasteUpload {

    private String mCityName;
    private String mIssueDescription;
    private String mGeolocation;

    private String mWasteImageUrl;

    public WasteUpload(){
        //empty constructor needed
    }

    public WasteUpload(String cityname, String issuedescription, String geolocation, String wasteimageurl){

        if (cityname.trim().equals("")){
            cityname = "No city or town name";

        }

        if (issuedescription.trim().equals("")){
            issuedescription = "No description";

        }

        if (geolocation.trim().equals("")){
            geolocation = "No location";

        }

        mCityName = cityname;
        mIssueDescription = issuedescription;
        mGeolocation = geolocation;
        mWasteImageUrl = wasteimageurl;



    }

    public String getCityName(){
        return mCityName;
    }

    public void setCityName(String cityName){
        mCityName = cityName;
    }


    public String getIssueDescription(){
        return mIssueDescription;
    }

    public void setIssueDescription(String issueDescription){
        mIssueDescription = issueDescription;
    }


    public String getGeolocation(){
        return mGeolocation;
    }

    public void setGeolocation(String geolocation){
        mGeolocation = geolocation;
    }


    public String getWasteImageUrl(){
        return mWasteImageUrl;
    }

    public void setWasteImageUrl(String wasteImageUrl){
        mWasteImageUrl = wasteImageUrl;
    }




}
