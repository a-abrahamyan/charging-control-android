package com.lifesoft.chc.api;

import com.lifesoft.chc.AppApplication;
import com.lifesoft.chc.constants.AppConstants;

public class ApiUtils {
    private ApiUtils() {}

    public static ApiService getAPIService() {

        return AppApplication.getClient(AppConstants.POST_BASE_URL).create(ApiService.class);
    }

}
