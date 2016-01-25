package anand.productreviews;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
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
import java.util.ArrayList;


public class DisplayReviews extends ActionBarActivity {
    String no_of_rev="";
    String prod_choice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reviews);
        Search();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_reviews, menu);
        return true;
    }

    public void Search()
    {
        MainActivity ma=new MainActivity();
        Choice ch=new Choice();
        no_of_rev=ch.selection;
        prod_choice=ma.prod_rev;
        Toast.makeText(this, no_of_rev+"Reviews", Toast.LENGTH_LONG).show();
        Toast.makeText(this, prod_choice, Toast.LENGTH_LONG).show();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            prod_choice = URLEncoder.encode(prod_choice);
            no_of_rev = URLEncoder.encode(no_of_rev);
            HttpGet httpget = new HttpGet("http://reviewrating.esy.es/getproductreviews.php?prod=" + prod_choice+"&number="+no_of_rev);
            HttpResponse response = httpclient.execute(httpget);
            BufferedReader br= new BufferedReader( new InputStreamReader(response.getEntity().getContent(),"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String rev="";
            String line = "";
            while( (line = br.readLine())!= null )
            {
                sb.append(line);
            }

            ArrayList<String> al = new ArrayList<String>();
            final TextView[] et=new TextView[10];
            LinearLayout layout=(LinearLayout)findViewById(R.id.layout1);
           // et.setMovementMethod(new ScrollingMovementMethod());
            try {
                JSONArray arr = new JSONArray( sb.toString() );
                JSONObject obj = arr.getJSONObject(0);
              if(no_of_rev.equals("1")){
                    al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    rev= al.get(0)+")"+al.get(1);
                    TextView nm=new TextView(this);
                    nm.setText(rev);
                    nm.setMaxLines(5);
                    layout.addView(nm);
                    et[0]=nm;

                      et[0].setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if(et[0].getMaxLines()==5) {
                                  et[0].setMaxLines(Integer.MAX_VALUE);
                              }
                              else{
                                  et[0].setMaxLines(5);
                              }

                          }
                      });


                }
                else if(no_of_rev.equals("3")){
                    al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    al.add( obj.getString("pr2"));
                    al.add(obj.getString("r2"));
                    al.add(obj.getString("pr3"));
                    al.add(obj.getString("r3"));
                   // rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5);


                  for(int i=0;i<3;i++){
                      TextView nm=new TextView(this);
                      nm.setText(rev);
                      nm.setMaxLines(5);
                      layout.addView(nm);
                      et[i]=nm;

                  }
                  et[0].setText(al.get(0)+")"+al.get(1));
                  et[1].setText(al.get(2) + ")" + al.get(3));
                  et[2].setText(al.get(4) + ")" + al.get(5));
                  et[0].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if(et[0].getMaxLines()==5) {
                              et[0].setMaxLines(Integer.MAX_VALUE);
                          }
                          else{
                              et[0].setMaxLines(5);
                          }

                      }
                  });

                  et[1].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[1].getMaxLines() == 5) {
                              et[1].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[1].setMaxLines(5);
                          }

                      }
                  });
                  et[2].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[2].getMaxLines() == 5) {
                              et[2].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[2].setMaxLines(5);
                          }

                      }
                  });

                  // et.setText(rev);
                }
                else if(no_of_rev.equals("5")){
                    al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    al.add( obj.getString("pr2"));
                    al.add( obj.getString("r2"));
                    al.add( obj.getString("pr3"));
                    al.add( obj.getString("r3"));
                    al.add( obj.getString("pr4"));
                    al.add( obj.getString("r4"));
                    al.add( obj.getString("pr5"));
                    al.add( obj.getString("r5"));
                    rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5)+"\n\n"+al.get(6)+")"+al.get(7)+"\n\n"+al.get(8)+")"+al.get(9);
                    //et.setText(rev);
                }
                else {
                    al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    al.add( obj.getString("pr2"));
                    al.add( obj.getString("r2"));
                    al.add( obj.getString("pr3"));
                    al.add( obj.getString("r3"));
                    al.add( obj.getString("pr4"));
                    al.add( obj.getString("r4"));
                    al.add( obj.getString("pr5"));
                    al.add( obj.getString("r5"));
                    al.add( obj.getString("pr6"));
                    al.add( obj.getString("r6"));
                    al.add( obj.getString("pr7"));
                    al.add( obj.getString("r7"));
                    al.add( obj.getString("pr8"));
                    al.add( obj.getString("r8"));
                    al.add( obj.getString("pr9"));
                    al.add( obj.getString("r9"));
                    al.add( obj.getString("pr10"));
                    al.add( obj.getString("r10"));
                    rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5)+"\n\n"+al.get(6)+")"+al.get(7)+"\n\n"+al.get(8)+")"+al.get(9)+"\n\n"+al.get(10)+")"+al.get(11)+"\n\n"+al.get(12)+")"+al.get(13)+"\n\n"+al.get(14)+")"+al.get(15)+"\n\n"+al.get(16)+")"+al.get(17)+"\n\n"+al.get(18)+")"+al.get(19);
                    //et.setText(rev);
                }












            } catch (JSONException e) {
                // TODO Auto-generated catch block
                //Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
