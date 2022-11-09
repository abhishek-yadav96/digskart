package com.example.digskart.Utils;

import org.json.JSONObject;

public interface WebServicesCallback {

    void OnJsonSuccess(JSONObject response);

    void OnFail(String response);

}
