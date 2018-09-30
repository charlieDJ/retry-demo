package com.retry.client;

import com.retry.exception.CustomMsgException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryAnnoService {
    @Autowired
    private OkHttpClient okHttpClient;

    @Retryable(value = {CustomMsgException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void service()  {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{}");
        Request request = new Request.Builder()
                .url("http://112.230.203.116:8001/hyfdsim/Api/extractCardNotice.html")
                .addHeader("Accept", "*/*")
                .post(requestBody)
                .build();
        System.out.println("do sth");
        try (Response response = okHttpClient.newCall(request).execute()) {

        } catch (IOException e) {
            throw new CustomMsgException("RPC调用异常");
        }
    }

    @Recover
    public void recover(CustomMsgException e) {
        System.out.println("----------------------");
        System.err.println(e.getMessage());
    }
}
