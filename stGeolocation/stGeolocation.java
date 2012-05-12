/**
 * 
 */
package neekey.phonegap.plugin.stGeolocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import android.util.Log;
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author neekey
 *
 */
public class stGeolocation extends Plugin implements LocationListener {
	
	private LocationManager lm;
    private Location currentLocation;
    private Context context;
    private Boolean ifInit = false;
    
    public static final String ACTION="get";
    
    private void init(){
    	
    	if( ifInit == false ){
    		
    		if( context == null ){
        		
        		context = this.ctx.getContext();
        	}
        	
        	if( lm == null ){
        		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        	}
        	
        	ifInit = true;
    	}
    }
    
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId ) {
		// TODO Auto-generated method stub
		PluginResult result = null;
		
		if( ACTION.equals( action ) ){
			
			// ��ʼ��
			init();
			// ��λ�ñ仯����
//			registerListenerPosition();
			// ��ȡ��ǰλ��
			Location location = getLocation();
			// �Ӵ�λ�ð�
			unregisterListenerPosition();
			
			try {
				JSONObject locationInfo = new JSONObject();
				locationInfo.put("latitude", location.getLatitude() );
				locationInfo.put( "longitude", location.getLongitude() );
				result = new PluginResult( Status.OK, locationInfo );
			}
			catch( JSONException jsonEx ) {
				
				result = new PluginResult( Status.JSON_EXCEPTION );
				
			}
		}
		else {
			
			result = new PluginResult( Status.INVALID_ACTION );
		}
		
		return result;
	}
	
	public Location getLocation() {   
    	
		Location location = null;
        
        while( location == null ){
        	
        	location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        	
        	if (location == null) {   
                location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);   
            } 
            
            if (location == null) {   
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);   
            }   
        }
        
        
        if( location != null ){
        	
        	currentLocation = location;
        	
        	Log.d( "tag", "currentLocation getSuccess" );
        }
        else {
        	
        	Log.d( "tag", "currentLocation getError" );
        }

        return location;   
    }
	
	/**
     * ����λ����Ϣ�仯
     */
    private void registerListenerPosition(){
    	lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, this);
    	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, this);
    	lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0,0, this);
    }
    
    /**
     * ȡ����λ����Ϣ�仯�ļ����İ�
     */
    private void unregisterListenerPosition(){
    	
    	lm.removeUpdates( this );
    }

	public void onLocationChanged(Location location) {
		
		currentLocation = location;
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
