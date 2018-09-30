package com.retry.client;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryService {

    @Autowired
    private OkHttpClient okHttpClient;

    public void service() throws IOException {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        // 重试操作
        String result = retryTemplate.execute(retryContext -> {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{}");
            Request request = new Request.Builder()
                    .url("http://112.230.203.116:8001/hyfdsim/Api/extractCardNotice.html")
                    .addHeader("Accept", "*/*")
                    .post(requestBody)
                    .build();
            System.err.println("正在请求" + retryContext.getRetryCount() + "次");
            try (Response response = okHttpClient.newCall(request).execute()) {

            }
            return "请求成功";
        }, retryContext -> "全部失败，执行恢复策略");
        System.err.println(result);
    }
}


