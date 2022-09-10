package com.mszlu.blog.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ginga
 * @version 1.0
 * @ClassName QiniuUtils
 * @Date 10/9/2022 上午10:12
 */
@Component
public class QiniuUtils {

    public static final String url = "http://rhz1dknvc.hd-bkt.clouddn.com/";

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.accessSecreteKey}")
    private String accessSecreteKey;

    public boolean upload(MultipartFile file, String filename) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String bucket = "natsuiroginga";

        try {
            final byte[] bytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecreteKey);
            final String token = auth.uploadToken(bucket);
            final Response response = uploadManager.put(bytes, filename, token);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
