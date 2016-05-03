package com.backendtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonCreateCommunity)
    void createCommunity()
    {
      Log.e("Game", "token = " + token);
        Ion.with(this)
                .load("http://pickmeasu.azurewebsites.net/api/create_community")
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .setStringBody("{\n" +
                                "    \n" +
                                "\"communityName\": \"New community " + new Random().nextInt(10000) +
                                "\"}")
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if (e != null)
                        {
                            Log.e("Game", "error " + e.getMessage());
                        } else
                            Log.e("Game", "test create community result " + result);
                    }
                });
    }

    @OnClick(R.id.buttonCreateCommunity2)
    void createCommunity2()
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", "application/json");
        String body = "{\n" +
                "    \n" +
                "\"communityName\": \"New community " + new Random().nextInt(10000) +
                "\"}";
        Fuel.post("http://pickmeasu.azurewebsites.net/api/create_community")
                .header(headers)
                .body(body, Charset.defaultCharset())
                .responseString(new Handler<String>()
                {
                    @Override
                    public void success(Request request, Response response, String s)
                    {
                        Log.e("Game", "success " + s + " " + response.toString());
                        Log.e("Game", "request was " + request.toString());
                    }

                    @Override
                    public void failure(Request request, Response response, FuelError fuelError)
                    {
                        Log.e("Game", "failed " + fuelError.getMessage() + " " + response.toString());
                    }
                });
    }

    @OnClick(R.id.buttonEditProfile)
    void editProfile()
    {
        // test edit profile

        JsonObject json2 = new JsonObject();
        json2.addProperty("bio", "new bio2");
        Ion.with(this)
                .load("http://pickmeasu.azurewebsites.net/api/EditProfile")
                .addHeader("Authorization", "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhMjU2IiwidHlwIjoiSldUIn0.eyJuYmYiOjE0NTc3MjA5MDUsImV4cCI6MTQ4OTI1NjkwNCwiaWF0IjoxNDU3NzIwOTA1LCJ1bmlxdWVfbmFtZSI6IjQzIiwiaXNzIjoiRXhhbXBsZUlzc3VlciIsImF1ZCI6IkV4YW1wbGVBdWRpZW5jZSJ9.eoya1ToYFlPkPh7vDeeRDOJY3eBjV0DL58NPOp9mrHC8vDozamtdUCUoMOjKqT1mgfu1_MAz-pU2T-t9GJsAqfI6j7OUXHzHEICSbgdmexnE85QpNX7-a-BTRvap9gNKPFm3BsDoNo6vSOCT-WSRggwdNl7ScJva9ylcB0fAsIq2jEJajPCq7bpHv8m8tV6UvF1x9G6RmYHoLo_53R64XD2FCH_TI-HdD6Y8s_cYjRxTpNJRlA9RVaymDP8ZqlWd7QBuApJGXpnSl6_RVkHN5GF4DBI1y1SYXloAR3vTMvqwB9NcrQ1JmXHFcYWmsA-cUyZI8W041HHSqeMn_1irAA")
                .addHeader("Content-Type", "application/json")
                .setStringBody("{\n" +
                        "    \n" +
                        "\"bio\": \"blabla4\",\n" +
                        "\"carAC\" :\"true\",\n" +
                        "\"carModel\": \"\",\n" +
                        "\"carPlateNumber\":\"\",\n" +
                        "\"carYear\" : \"1980\"  ,\n" +
                        "\"dob\" :\"11-3-2016\"  ,\n" +
                        "\"fbLink\":\"https://www.facebook.com/fromapk\",\n" +
                        "\"firstName\" :\"From\",\n" +
                        "\"googleLink\" :\"https://plus.google.com/u/\",\n" +
                        "\"lastName\": \"Apk\",\n" +
                        "\"residence\" :\"in Bluestacks\",\n" +
                        "\"numberOfRides\" : \"0\",\n" +
                        "\"points\" : \"0\" \n" +
                        "   \n" +
                        "}")
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if (e != null)
                        {
                            Log.e("Game", "error " + e.getMessage());
                        } else
                            Log.e("Game", "test edit profile result " + result);
                    }
                });
    }

    @OnClick(R.id.buttonTestPost)
    void testPost()
    {
        Ion.with(this)
                .load("http://pickmetest.azurewebsites.net/api/test")
                .setHeader("Content-Type", "application/json")
                .setStringBody("{\n" +
                        "    \"value\": " + new Random().nextInt(1000) + "\n" +
                        "}")
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if (e != null)
                        {
                            Log.e("Game", "error " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else
                        {
                            Log.e("Game", "test post result " + result);
                            Toast.makeText(getApplicationContext(), "test post result " + result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick(R.id.buttonTestGet)
    void testGet()
    {
        String url = "http://pickmetest.azurewebsites.net/api/test_by_get?value=blabla" + new Random().nextInt(1000);
        Ion.with(this)
                .load("GET", url)
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if (e != null)
                        {
                            Log.e("Game", "error " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else
                        {
                            Log.e("Game", "test get result " + result);
                            Toast.makeText(getApplicationContext(), "test get result " + result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    @OnClick(R.id.buttonLogIn)
    void testLogIn()
    {
        String url = "http://pickmeasu.azurewebsites.net/api/login?email=test@mail.com&password=incorrect";
        Ion.with(this)
                .load("GET", url)
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if (e != null)
                        {
                            Log.e("Game", "error " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else
                        {
                            Log.e("Game", "test login result " + result);
                            Toast.makeText(getApplicationContext(), "test login result " + result, Toast.LENGTH_SHORT).show();

                            try {
                                JSONObject json = new JSONObject(result);
                                token = json.getString("token");

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });



    }

    @OnClick(R.id.buttonSignUp)
    void signUp()
    {
        Ion.with(this)
                //    .load("GET", url)
                //    .asString()
                .load("http://pickmeasu.azurewebsites.net/api/sign_up")
                .addHeader("Content-Type", "application/json")
                .setStringBody("{\n" +
                        "    \n" +
                        "\"email\": \"signupbypost" + new Random().nextInt(10000) + "@mail.com\",\n" +
                        "\"password\": \"bazinga\",\n" +
                        "\"firstName\" :\"m\",\n" +
                        "\"lastName\" : \"n\"  ,\n" +
                        "\"gender\" :\"False\"  ,\n" +
                        "   \n" +
                        "}")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Log.e("Game", "error " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Game", "test sign up get result " + result);
                            Toast.makeText(getApplicationContext(), "test sign up by get result " + result, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
