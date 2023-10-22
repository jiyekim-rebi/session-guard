package com.session_guard.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.session_guard.api.dao.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

// api 호출 공통작업 인터셉트
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==================== BEGIN ====================");
        log.info("Request URI : " + request.getRequestURI());
        log.info("Request Method : " + request.getMethod());
        if (request.getMethod().equals("POST")) {
            InputStream inputStream = request.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            String requestBody = result.toString("UTF-8");
            log.info("Request Body : " + requestBody);

            if (requestBody.isEmpty()){
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(new Response("request body is empty"));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                return false;
            } else {
                // TODO : body가 있는 경우 다시 스트림 생성해서 보내줌

            }

        }
        return true;
    }


}
