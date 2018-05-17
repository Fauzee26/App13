package fauzi.hilmy.app13.ApiRetrofit;

import fauzi.hilmy.app13.ResponseServer.ResponseReadData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v1/Country/getCountries")
    Call<ResponseReadData> response_read_data();
}