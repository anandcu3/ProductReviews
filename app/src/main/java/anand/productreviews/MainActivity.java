package anand.productreviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.FragmentManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;


public class MainActivity extends FragmentActivity {

    String addp="";
    public static String prod_rev="";
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AddSpinner().execute();
        c = this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    public void Search(View v){
        Spinner mySpinner=(Spinner) findViewById(R.id.products_spinner);
        prod_rev = mySpinner.getSelectedItem().toString();
        Intent launchActivity2 = new Intent(MainActivity.this, DisplayReviews.class);
        startActivity(launchActivity2 );
    }
    public void addprod() {

        try {
            HttpClient httpclient = new DefaultHttpClient();
            addp = URLEncoder.encode(addp);
            HttpGet httpget = new HttpGet("http://reviewrating.esy.es/addnewproduct.php?pname=" + addp);
            HttpResponse response = httpclient.execute(httpget);
            String stat = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"), 8);
            stat = br.readLine().trim();
            if (stat.contains("OK")) {
                Toast.makeText(c, "Please check back in sometime while we look for some reviews!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(c, "Please try again! Could not reach server!", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SelectNumber(View v){
        FragmentManager fm = getFragmentManager();
        Choice my_dialog= new Choice();
        my_dialog.show(fm, "my_dialog");
    }

    public void AddProduct(View v){

        final EditText e_addproduct=(EditText) findViewById(R.id.add);
        Button add=(Button) findViewById(R.id.button2);
        addp=e_addproduct.getText().toString();
        if(addp.equals("")||addp.equals(null)){
            Toast.makeText(c, "Please Enter the product!!", Toast.LENGTH_LONG).show();
        }
        else {
            addprod();
        }
    }

    //AsyncTask to add to spinner
    private class AddSpinner extends AsyncTask<String,JSONObject,String[]> {


        @Override
        protected String[] doInBackground(String... arg) {
            // TODO Auto-generated method stub
            try {
                HttpClient client = new DefaultHttpClient();


                HttpGet post = new HttpGet("http://reviewrating.esy.es/productlist.php");


                HttpResponse response = client.execute(post);


                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                String products = sb.toString();
                String[] lp = products.split(",");
                return lp;
                //ArrayList<MyRow> al = new ArrayList<MyRow>();

            /*    try{

                    JSONArray arr = new JSONArray( sb.toString() );
                    for( int i=0 ;i<arr.length()  ; i++)
                    {
                        JSONObject obj = arr.getJSONObject(i);

                        al.add( new MyRow( obj.getString("name") , obj.getString("msg"))   );
                    }
                    return al;
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    //Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
*/

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                //publishProgress();
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //publishProgress();
                e.printStackTrace();
            }
            return new String[]{"empty"};
        }




        protected void onPostExecute(String[] lp) {
            Spinner s = (Spinner) findViewById(R.id.products_spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,android.R.layout.simple_spinner_item,lp);
            s.setAdapter(adapter);
            //Toast.makeText(c,"Spinner ge add aaytu", Toast.LENGTH_SHORT).show();
        }
    }
}
