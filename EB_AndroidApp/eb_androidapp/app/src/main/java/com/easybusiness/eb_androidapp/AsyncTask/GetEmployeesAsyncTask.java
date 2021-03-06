package com.easybusiness.eb_androidapp.AsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.easybusiness.eb_androidapp.Entities.Users;
import com.easybusiness.eb_androidapp.R;
import com.easybusiness.eb_androidapp.UI.Adapters.EmployeeAdapter;
import com.easybusiness.eb_androidapp.UI.Fragments.ViewEmployeesFragment;
import com.easybusiness.eb_androidapp.UI.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/*

Sample query builder:

Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("username", edit_username.getText().toString())
                .appendQueryParameter("password", edit_password.getText().toString());

            String query = builder.build().getEncodedQuery();
 */

public class GetEmployeesAsyncTask extends AsyncTask<Void, Void, Void> {

    private String query;
    private String responseData;
    private Activity activity;
    private View view;
    ArrayList<Users> employees = null;

    public GetEmployeesAsyncTask(String query, Activity activity, View view) {
        this.query = query;
        this.activity = activity;
        this.view = view;
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (query == null) query = "";

        try {
            URL url = new URL(AsyncTasks.encodeForAPI(activity.getString(R.string.baseURL), "Users", "GetMultiple"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            byte[] outputBytes = query.getBytes("UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            OutputStream os = urlConnection.getOutputStream();
            os.write(outputBytes);
            os.close();
            int statusCode = urlConnection.getResponseCode();
            urlConnection.disconnect();

            //OK
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                responseData = AsyncTasks.convertStreamToString(inputStream);

                JSONObject outterObject = new JSONObject(responseData);

                final String status = outterObject.getString("Status");
                final String title = outterObject.getString("Title");
                final String message = outterObject.getString("Message");

                if (status.equals(AsyncTasks.RESPONSE_OK)) {
                    employees = new ArrayList<>();
                    JSONArray dataArray = outterObject.getJSONArray("Data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        String firstName = jsonObject.getString("Firstname");
                        String lastName = jsonObject.getString("Lastname");
                        String telephone = jsonObject.getString("Telephone");
                        int userlevelID = jsonObject.getInt("UserLevelID");
                        int id = jsonObject.getInt("UserID");
                        int dateHired = jsonObject.getInt("DateHired");
                        String city = jsonObject.getString("City");
                        String address = jsonObject.getString("Address");
                        int countryID = jsonObject.getInt("CountryID");
                        String username = jsonObject.getString("Username");
                        Users p = new Users(id, username, "", userlevelID, firstName, lastName, dateHired, city, address, telephone, countryID);
                        employees.add(p);
                    }
                    MainActivity mainActivity = (MainActivity) activity;
                    mainActivity.EMPLOYEES_DATA = employees;
                    final ListView employeesListview = activity.findViewById(R.id.employees_list_view);
//                    String [] items = new String[employees.size()];
//                    for (int i = 0; i < items.length; i++)
//                        items[i] = employees.get(i).getFirstname();
                    final EmployeeAdapter employeeAdapter = new EmployeeAdapter(activity, employees);
                    ViewEmployeesFragment.allEmployeesAdapter = (EmployeeAdapter) employeesListview.getAdapter();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            employeesListview.setAdapter(employeeAdapter);

                            final View progressView = activity.findViewById(R.id.view_employees_progress);
                            final View employeeView = activity.findViewById(R.id.view_employees_view);
                            progressView.setVisibility(View.GONE);
                            employeeView.setVisibility(View.VISIBLE);

                        }
                    });


                } else if (status.equals(AsyncTasks.RESPONSE_ERROR)) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final AlertDialog alertDialog = AsyncTasks.createGeneralErrorDialog(activity, title, message);
                            alertDialog.show();
                        }
                    });
                }


            }
            //CONNECTION ERROR
            else {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog alertDialog = AsyncTasks.createConnectionErrorDialog(activity);
                        alertDialog.show();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}