package com.vreal.commom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("vreal")
public class VrealApiProperty {

    private String origemPermitida = "http://localhost:800";
    private String originPermitida2 = "http://localhost:8000";

    private final Seguranca seguranca = new Seguranca();
    private final Mail mail = new Mail();


//    public static class S3 {
//
//        private String accessKeyId;
//
//        private String secretAccessKey;
//
//        private String bucket = "aw-arimoney-arquivos";
//
//        public String getBucket() {
//            return bucket;
//        }
//
//        public void setBucket(String bucket) {
//            this.bucket = bucket;
//        }
//
//        public String getAccessKeyId() {
//            return accessKeyId;
//        }
//
//        public void setAccessKeyId(String accessKeyId) {
//            this.accessKeyId = accessKeyId;
//        }
//
//        public String getSecretAccessKey() {
//            return secretAccessKey;
//        }
//
//        public void setSecretAccessKey(String secretAccessKey) {
//            this.secretAccessKey = secretAccessKey;
//        }
//
//    }

    @Getter
    @Setter
    public static class Seguranca {
        private boolean enableHttps;
    }

    @Getter
    @Setter
    public static class Mail {

        private String host;

        private Integer port;

        private String username;

        private String password;



    }
}
