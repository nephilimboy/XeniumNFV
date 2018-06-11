package com.ahghorab.xenonet.service.util.httpReq;

import com.ahghorab.xenonet.web.rest.vm.pojo.NetworkTopologyPOJO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpRequestSender {
    private String uri;
    private StringEntity reqBody;
    private String resBody;
    private int resStatus;
    private HttpRequestTypeEnum reqType;

    public HttpRequestSender(String uri, StringEntity reqBody, HttpRequestTypeEnum reqType) {
        this.uri = uri;
        this.reqBody = reqBody;
        this.reqType = reqType;
    }

    public void execute() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            //Set Authentication
            String auth = "admin" + ":" + "admin";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("ISO-8859-1")));
            String authHeader = "Basic " + new String(encodedAuth);

            ResponseHandler<String> responseHandler = response -> {
                this.resStatus = response.getStatusLine().getStatusCode();
                if (this.resStatus >= 200 && this.resStatus < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + this.resStatus);
                }
            };

            if (this.reqType == HttpRequestTypeEnum.GET) {
                HttpGet httpReq = new HttpGet(this.uri);
                httpReq.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
                this.resBody = httpclient.execute(httpReq, responseHandler);
            } else if (this.reqType == HttpRequestTypeEnum.PUT) {
                HttpPut httpReq = new HttpPut(this.uri);
                httpReq.setEntity(reqBody);
                httpReq.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
                this.resBody = httpclient.execute(httpReq, responseHandler);
            } else if (this.reqType == HttpRequestTypeEnum.POST) {
                HttpPost httpReq = new HttpPost(this.uri);
                httpReq.setEntity(reqBody);
                httpReq.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
                this.resBody = httpclient.execute(httpReq, responseHandler);
            } else {
                HttpDelete httpReq = new HttpDelete(this.uri);
                httpReq.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
                this.resBody = httpclient.execute(httpReq, responseHandler);
            }

            System.out.println("----------------------------------------");
            System.out.println(this.resBody);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public NetworkTopologyPOJO getMappedResBody() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            NetworkTopologyPOJO entity = mapper.readValue(this.resBody, NetworkTopologyPOJO.class);
//            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
//            System.out.println(prettyStaff1);
            return entity;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResBody() {
        return resBody;
    }

    public int getResStatus() {
        return resStatus;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public StringEntity getReqBody() {
        return reqBody;
    }

    public void setReqBody(StringEntity reqBody) {
        this.reqBody = reqBody;
    }

    public HttpRequestTypeEnum getReqType() {
        return reqType;
    }

    public void setReqType(HttpRequestTypeEnum reqType) {
        this.reqType = reqType;
    }

}
