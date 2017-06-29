package socialboards.echidnanative.com.test.model;


/**
 * Created by vali on 29/06/17.
 */

public class Maps
{
    private Listings[] listings;

    private boolean success;

    public Listings[] getListings ()
    {
        return listings;
    }

    public void setListings (Listings[] listings)
    {
        this.listings = listings;
    }

    public boolean getSuccess ()
    {
        return success;
    }

    public void setSuccess (boolean success)
    {
        this.success = success;
    }


}
