package com.example.wsrestful.interfaces;
import com.example.wsrestful.models.revista;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface revistaService {

    @GET("ws//issues.php")
    Call<List<revista>> findO(@Query("j_id") String q);
}
