//package com.zyg.basicutils.springmvc;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Iterator;
//
///**
// * Spring中的文件上传
// * Created by Mo on 2017/8/31.
// */
//public class FileUpload {
//
//    private static final String DOMAIN = "http://localhost:8080";//域名
//
//    private String upload(HttpServletRequest request, String type) {
//        //创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //判断 request 是否有文件上传,即多部分请求
//        if (multipartResolver.isMultipart(request)) {
//            //转换成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            //取得request中的所有文件名
//            Iterator<String> iter = multiRequest.getFileNames();
//            while (iter.hasNext()) {
//                //取得上传文件
//                MultipartFile file = multiRequest.getFile(iter.next());
//                if (file != null) {
//                    //取得当前上传文件的文件名称
//                    String myFileName = file.getOriginalFilename();
//                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
//                    if (myFileName.trim() != "") {
//                        System.out.println(myFileName);
//                        //重命名上传后的文件名,以固定前缀加当前时间加用户id命名
//                        String fileName = type + "-" + initUserId() + "-" + System.currentTimeMillis() + "." + myFileName.substring(myFileName.lastIndexOf('.') + 1);
//                        //定义上传路径
//                        String path = request.getServletContext().getRealPath("/static/" + type + "/" + fileName);
//                        System.out.println("上传的真实路径是" + path);
//                        File localFile = new File(path);
//                        try {
//                            file.transferTo(localFile);
//                            System.out.println("已成功上传文件");
//                        } catch (IOException e) {
//                            return "error";
//                        }
//                        if ("img".equals(type) || "avatar".equals(type))//如果是图片需要传回预览地址--该条用于上传图片等需要回传图片地址的场景
//                            return DOMAIN + "/static/" + type + "/" + fileName;
//                        else {//对于需要保存到数据库的文件信息来说，保存信息到数据库后返回插入的记录的id
//                            Attachment attachment = new Attachment(initUserId(), new Date(), fileName, file.getSize(), "/static/file/" + fileName);
//                            if (attachmentService.insert(attachment)) {
//                                return String.valueOf(attachment.getId());
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//        return "error";
//    }
//
//}
