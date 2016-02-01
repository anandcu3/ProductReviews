package anand.productreviews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

        return true;
    }

    public void Search()
    {
        MainActivity ma=new MainActivity();
        Choice ch=new Choice();
        no_of_rev=ch.selection;
        prod_choice=ma.prod_rev;
        setTitle(prod_choice+" : "+no_of_rev+" Reviews");
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
            //Toast.makeText(this,sb.toString(),Toast.LENGTH_LONG).show();
            ArrayList<String> al = new ArrayList<String>();
            final TextView[] et=new TextView[10];
            LinearLayout layout=(LinearLayout)findViewById(R.id.layout1);
           // et.setMovementMethod(new ScrollingMovementMethod());
            try {
                JSONArray arr = new JSONArray( sb.toString() );
                JSONObject obj = arr.getJSONObject(0);
              if(no_of_rev.equals("1")){
                    //al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    rev=al.get(0);
                   String revs[]=rev.split("\\|");
                    rev=revs[2].trim();
                  //  int rt= Integer.parseInt(al.get(0).trim());
                    //String rating="";
                    //for (int i=0 ; i<5 ;i++){
                      //  if(i<rt){
                        //    rating=rating+"\u2605";
                        //}
                       // else rating=rating+"\u2606";
                    //}
                  String content = "";

                  TextView nm=new TextView(this);
                  if(!revs[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content = content + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content=content+"<br/>";
                  }
                  if(!revs[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content = content + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                    content="<br/><br/><b>"+content+"</b><br/><br/>"+ rev;
                    nm.setText(Html.fromHtml(content));
                    nm.setMaxLines(9);
                    layout.addView(nm);
                    et[0]=nm;

                      et[0].setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if(et[0].getMaxLines()==9) {
                                  et[0].setMaxLines(Integer.MAX_VALUE);
                              }
                              else{
                                  et[0].setMaxLines(9);
                              }

                          }
                      });


                }
                else if(no_of_rev.equals("3")){
                    //al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    //al.add( obj.getString("pr2"));
                    al.add(obj.getString("r2"));
                    //al.add(obj.getString("pr3"));
                    al.add(obj.getString("r3"));
                   // rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5);


                  for(int i=0;i<3;i++){
                      TextView nm=new TextView(this);
                      nm.setText(rev);
                      nm.setMaxLines(9);
                      layout.addView(nm);
                      et[i]=nm;

                  }
                  //int rt= Integer.parseInt(al.get(0).trim());
                  //String rating="";
                  //for (int i=0 ; i<5 ;i++){
                   //   if(i<rt){
                     //     rating=rating+"\u2605";
                      //}
                      //else rating=rating+"\u2606";
                  //}
                  String content="";
                  String revs[]=al.get(0).split("\\|");
                  if(!revs[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content = content + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content=content+"<br/>";
                  }
                  if(!revs[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content = content + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content="<br/><br/><b>"+content+"</b><br/><br/>"+ revs[2].trim();
                  et[0].setText(Html.fromHtml(content));


                  //int rt1= Integer.parseInt(al.get(2).trim());
                  //String rating1="";
                  //for (int i=0 ; i<5 ;i++){
                      //if(i<rt1){
                       //   rating1=rating1+"\u2605";
                      //}
                    //  else rating1=rating1+"\u2606";
                  //}
                  String content1="";
                  String revs1[]=al.get(1).split("\\|");
                  if(!revs1[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs1[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content1 = content1 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content1=content1+"<br/>";
                  }
                  if(!revs1[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs1[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content1 = content1 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content1="<br/><br/><b>"+content1+"</b><br/><br/>"+ revs1[2].trim();
                  et[1].setText(Html.fromHtml(content1));


                  //int rt2= Integer.parseInt(al.get(4).trim());
                  //String rating2="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt2){
                      //    rating2=rating2+"\u2605";
                      //}
                      //else rating2=rating2+"\u2606";
                  //}

                  String content2="";
                  String revs2[]=al.get(2).split("\\|");
                  if(!revs2[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs2[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content2 = content2 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content2=content2+"<br/>";
                  }
                  if(!revs2[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs2[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content2 = content2 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content2="<br/><br/><b>"+content2+"</b><br/><br/>"+ revs2[2].trim();
                  et[2].setText(Html.fromHtml(content2));

                  et[0].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[0].getMaxLines() == 9) {
                              et[0].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[0].setMaxLines(9);
                          }

                      }
                  });

                  et[1].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[1].getMaxLines() == 9) {
                              et[1].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[1].setMaxLines(9);
                          }

                      }
                  });
                  et[2].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[2].getMaxLines() == 9) {
                              et[2].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[2].setMaxLines(9);
                          }

                      }
                  });

                  // et.setText(rev);
                }
                else if(no_of_rev.equals("5")){
                    //al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    //al.add( obj.getString("pr2"));
                    al.add( obj.getString("r2"));
                    //al.add( obj.getString("pr3"));
                    al.add( obj.getString("r3"));
                    //al.add( obj.getString("pr4"));
                    al.add( obj.getString("r4"));
                    //al.add( obj.getString("pr5"));
                    al.add(obj.getString("r5"));
                  for(int i=0;i<5;i++){
                      TextView nm=new TextView(this);
                      nm.setText(rev);
                      nm.setMaxLines(9);
                      layout.addView(nm);
                      et[i]=nm;
                  }
                  //int rt= Integer.parseInt(al.get(0).trim());
                  //String rating="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt){
                      //    rating=rating+"\u2605";
                      //}
                      //else rating=rating+"\u2606";
                  //}
                  String content="";
                  String revs[]=al.get(0).split("\\|");
                  if(!revs[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content = content + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content=content+"<br/>";
                  }
                  if(!revs[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content = content + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content="<br/><br/><b>"+content+"</b><br/><br/>"+ revs[2].trim();
                  et[0].setText(Html.fromHtml(content));


                  //int rt1= Integer.parseInt(al.get(2).trim());
                  //String rating1="";
                 // for (int i=0 ; i<5 ;i++){
                   //   if(i<rt1){
                     //     rating1=rating1+"\u2605";
                      //}
                      //else rating1=rating1+"\u2606";
                  //}
                  String content1="";
                  String revs1[]=al.get(1).split("\\|");
                  if(!revs1[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs1[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content1 = content1 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content1=content1+"<br/>";
                  }
                  if(!revs1[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs1[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content1 = content1 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content1="<br/><br/><b>"+content1+"</b><br/><br/>"+ revs1[2].trim();
                  et[1].setText(Html.fromHtml(content1));


                  //int rt2= Integer.parseInt(al.get(4).trim());
                  //String rating2="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt2){
                      //    rating2=rating2+"\u2605";
                      //}
                      //else rating2=rating2+"\u2606";
                  //}
                  String content2="";
                  String revs2[]=al.get(2).split("\\|");
                  if(!revs2[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs2[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content2 = content2 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content2=content2+"<br/>";
                  }
                  if(!revs2[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs2[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content2 = content2 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content2="<br/><br/><b>"+content2+"</b><br/><br/>"+ revs2[2].trim();
                  et[2].setText(Html.fromHtml(content2));


                  //int rt3= Integer.parseInt(al.get(6).trim());
                  //String rating3="";
                  //for (int i=0 ; i<5 ;i++){
                      //if(i<rt3){
                        //  rating3=rating3+"\u2605";
                      //}
                    //  else rating3=rating3+"\u2606";
                  //}
                  String content3="";
                  String revs3[]=al.get(3).split("\\|");
                  if(!revs3[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs3[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content3 = content3 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content3=content3+"<br/>";
                  }
                  if(!revs3[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs3[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content3 = content3 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content3="<br/><br/><b>"+content3+"</b><br/><br/>"+ revs3[2].trim();
                  et[3].setText(Html.fromHtml(content3));

                  //int rt4= Integer.parseInt(al.get(8).trim());
                  //String rating4="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt4){
                          //rating4=rating4+"\u2605";
                      //}
                      //else rating4=rating4+"\u2606";
                  //}
                  String content4="";
                  String revs4[]=al.get(4).split("\\|");
                  if(!revs4[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs4[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content4 = content4 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content4=content4+"<br/>";
                  }
                  if(!revs4[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs4[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content4 = content4 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content4="<br/><br/><b>"+content4+"</b><br/><br/>"+ revs4[2].trim();
                  et[4].setText(Html.fromHtml(content4));


                  et[0].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[0].getMaxLines() == 9) {
                              et[0].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[0].setMaxLines(9);
                          }

                      }
                  });

                  et[1].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[1].getMaxLines() == 9) {
                              et[1].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[1].setMaxLines(9);
                          }

                      }
                  });
                  et[2].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[2].getMaxLines() == 9) {
                              et[2].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[2].setMaxLines(9);
                          }

                      }
                  });
                  et[3].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[3].getMaxLines() == 9) {
                              et[3].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[3].setMaxLines(9);
                          }

                      }
                  });

                  et[4].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[4].getMaxLines() == 9) {
                              et[4].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[4].setMaxLines(9);
                          }

                      }
                  });



                  //rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5)+"\n\n"+al.get(6)+")"+al.get(7)+"\n\n"+al.get(8)+")"+al.get(9);
                    //et.setText(rev);
                }
                else {
                    //al.add( obj.getString("pr1"));
                    al.add( obj.getString("r1"));
                    //al.add( obj.getString("pr2"));
                    al.add( obj.getString("r2"));
                    //al.add( obj.getString("pr3"));
                    al.add( obj.getString("r3"));
                    //al.add( obj.getString("pr4"));
                    al.add( obj.getString("r4"));
                    //al.add( obj.getString("pr5"));
                    al.add( obj.getString("r5"));
                    //al.add( obj.getString("pr6"));
                    al.add( obj.getString("r6"));
                    //al.add( obj.getString("pr7"));
                    al.add( obj.getString("r7"));
                    //al.add( obj.getString("pr8"));
                    al.add( obj.getString("r8"));
                    //al.add( obj.getString("pr9"));
                    al.add( obj.getString("r9"));
                    //al.add( obj.getString("pr10"));
                    al.add( obj.getString("r10"));
                  for(int i=0;i<10;i++){
                      TextView nm=new TextView(this);
                      nm.setText(rev);
                      nm.setMaxLines(9);
                      layout.addView(nm);
                      et[i]=nm;

                  }
               //   int rt= Integer.parseInt(al.get(0).trim());
                 // String rating="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt){
                      //    rating=rating+"\u2605";
                      //}
                      //else rating=rating+"\u2606";
                  //}
                  String content="";
                  String revs[]=al.get(0).split("\\|");
                  if(!revs[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content = content + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content=content+"<br/>";
                  }
                  if(!revs[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content = content + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content="<br/><br/><b>"+content+"</b><br/><br/>"+ revs[2].trim();
                  et[0].setText(Html.fromHtml(content));


              //    int rt1= Integer.parseInt(al.get(2).trim());
                //  String rating1="";
                  //for (int i=0 ; i<5 ;i++){
                    //  if(i<rt1){
                      //    rating1=rating1+"\u2605";
                      //}
                      //else rating1=rating1+"\u2606";
                  //}
                  String content1="";
                  String revs1[]=al.get(1).split("\\|");
                  if(!revs1[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs1[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content1 = content1 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content1=content1+"<br/>";
                  }
                  if(!revs1[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs1[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content1 = content1 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content1="<br/><br/><b>"+content1+"</b><br/><br/>"+ revs1[2].trim();
                  et[1].setText(Html.fromHtml(content1));




                  //      int rt2= Integer.parseInt(al.get(4).trim());
              //    String rating2="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt2){
                    //      rating2=rating2+"\u2605";
                      //}
                     // else rating2=rating2+"\u2606";
                  //}
                  String content2="";
                  String revs2[]=al.get(2).split("\\|");
                  if(!revs2[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs2[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content2 = content2 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content2=content2+"<br/>";
                  }
                  if(!revs2[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs2[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content2 = content2 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content2="<br/><br/><b>"+content2+"</b><br/><br/>"+ revs2[2].trim();
                  et[2].setText(Html.fromHtml(content2));


            //      int rt3= Integer.parseInt(al.get(6).trim());
              //    String rating3="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt3){
                    //      rating3=rating3+"\u2605";
                      //}
                      //else rating3=rating3+"\u2606";
                  //}
                  String content3="";
                  String revs3[]=al.get(3).split("\\|");
                  if(!revs3[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs3[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content3 = content3 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content3=content3+"<br/>";
                  }
                  if(!revs3[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs3[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content3 = content3 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content3="<br/><br/><b>"+content3+"</b><br/><br/>"+ revs3[2].trim();
                  et[3].setText(Html.fromHtml(content3));


            //      int rt4= Integer.parseInt(al.get(8).trim());
              //    String rating4="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt4){
                    //      rating4=rating4+"\u2605";
                      //}
                      //else rating4=rating4+"\u2606";
                  //}
                  String content4="";
                  String revs4[]=al.get(4).split("\\|");
                  if(!revs4[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs4[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content4 = content4 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content4=content4+"<br/>";
                  }
                  if(!revs4[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs4[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content4 = content4 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content4="<br/><br/><b>"+content4+"</b><br/><br/>"+ revs4[2].trim();
                  et[4].setText(Html.fromHtml(content4));


            //      int rt5= Integer.parseInt(al.get(10).trim());
              //    String rating5="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt5){
                    //      rating5=rating5+"\u2605";
                      //}
                      //else rating5=rating5+"\u2606";
                  //}
                  String content5="";
                  String revs5[]=al.get(5).split("\\|");
                  if(!revs5[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs5[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content5 = content5 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content5=content5+"<br/>";
                  }
                  if(!revs5[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs5[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content5 = content5 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content5="<br/><br/><b>"+content5+"</b><br/><br/>"+ revs5[2].trim();
                  et[5].setText(Html.fromHtml(content5));

            //      int rt6= Integer.parseInt(al.get(12).trim());
              //    String rating6="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt6){
                    //      rating6=rating6+"\u2605";
                      //}
                      //else rating6=rating6+"\u2606";
                  //}
                  String content6="";
                  String revs6[]=al.get(6).split("\\|");
                  if(!revs6[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs6[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content6 = content6 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content6=content6+"<br/>";
                  }
                  if(!revs6[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs6[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content6 = content6 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content6="<br/><br/><b>"+content6+"</b><br/><br/>"+ revs6[2].trim();
                  et[6].setText(Html.fromHtml(content6));

            //      int rt7= Integer.parseInt(al.get(14).trim());
              //    String rating7="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt7){
                    //      rating7=rating7+"\u2605";
                      //}
                     // else rating7=rating7+"\u2606";
                  //}
                  String content7="";
                  String revs7[]=al.get(7).split("\\|");
                  if(!revs7[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs7[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content7 = content7 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content7=content7+"<br/>";
                  }
                  if(!revs7[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs7[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content7 = content7 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content7="<br/><br/><b>"+content7+"</b><br/><br/>"+ revs7[2].trim();
                  et[7].setText(Html.fromHtml(content7));

            //      int rt8= Integer.parseInt(al.get(16).trim());
              //    String rating8="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt8){
                    //      rating8=rating8+"\u2605";
                      //}
                      //else rating8=rating8+"\u2606";
                  //}
                  String content8="";
                  String revs8[]=al.get(8).split("\\|");
                  if(!revs8[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs8[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content8 = content8 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content8=content8+"<br/>";
                  }
                  if(!revs8[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs8[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content8 = content8 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content8="<br/><br/><b>"+content8+"</b><br/><br/>"+ revs8[2].trim();
                  et[8].setText(Html.fromHtml(content8));

            //      int rt9= Integer.parseInt(al.get(18).trim());
              //    String rating9="";
                //  for (int i=0 ; i<5 ;i++){
                  //    if(i<rt9){
                    //      rating9=rating9+"\u2605";
                     // }
                      //else rating9=rating9+"\u2606";
                  //}
                  String content9="";
                  String revs9[]=al.get(9).split("\\|");
                  if(!revs9[0].equalsIgnoreCase("empty")) {
                      String pattr[] = revs9[0].split(",");

                      for (int i = 0; i < pattr.length; i++) {
                          pattr[i] = pattr[i].substring(0, 1).toUpperCase() + pattr[i].substring(1);
                          content9 = content9 + pattr[i]+" &nbsp;" + "\uD83D\uDC96" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                      content9=content9+"<br/>";
                  }
                  if(!revs9[1].equalsIgnoreCase("empty")) {
                      String nattr[] = revs9[1].split(",");
                      for (int i = 0; i < nattr.length; i++) {
                          nattr[i] = nattr[i].substring(0, 1).toUpperCase() + nattr[i].substring(1);
                          content9 = content9 + nattr[i] + "&nbsp;"+"\uD83D\uDC94" + "&nbsp;&nbsp;&nbsp;&nbsp;";
                      }
                  }
                  content9="<br/><br/><b>"+content9+"</b><br/><br/>"+ revs9[2].trim();
                  et[9].setText(Html.fromHtml(content9));


                  et[0].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[0].getMaxLines() == 9) {
                              et[0].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[0].setMaxLines(9);
                          }

                      }
                  });

                  et[1].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[1].getMaxLines() == 9) {
                              et[1].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[1].setMaxLines(9);
                          }

                      }
                  });
                  et[2].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[2].getMaxLines() == 9) {
                              et[2].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[2].setMaxLines(9);
                          }

                      }
                  });
                  et[3].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[3].getMaxLines() == 9) {
                              et[3].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[3].setMaxLines(9);
                          }

                      }
                  });

                  et[4].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[4].getMaxLines() == 9) {
                              et[4].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[4].setMaxLines(9);
                          }

                      }
                  });
                  et[5].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[5].getMaxLines() == 9) {
                              et[5].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[5].setMaxLines(9);
                          }

                      }
                  });

                  et[6].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[6].getMaxLines() == 9) {
                              et[6].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[6].setMaxLines(9);
                          }

                      }
                  });
                  et[7].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[7].getMaxLines() == 9) {
                              et[7].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[7].setMaxLines(9);
                          }

                      }
                  });
                  et[8].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[8].getMaxLines() == 9) {
                              et[8].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[8].setMaxLines(9);
                          }

                      }
                  });

                  et[9].setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (et[9].getMaxLines() == 9) {
                              et[9].setMaxLines(Integer.MAX_VALUE);
                          } else {
                              et[9].setMaxLines(9);
                          }

                      }
                  });
                    //rev=al.get(0)+")"+al.get(1)+"\n\n"+al.get(2)+")"+al.get(3)+"\n\n"+al.get(4)+")"+al.get(5)+"\n\n"+al.get(6)+")"+al.get(7)+"\n\n"+al.get(8)+")"+al.get(9)+"\n\n"+al.get(10)+")"+al.get(11)+"\n\n"+al.get(12)+")"+al.get(13)+"\n\n"+al.get(14)+")"+al.get(15)+"\n\n"+al.get(16)+")"+al.get(17)+"\n\n"+al.get(18)+")"+al.get(19);
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
