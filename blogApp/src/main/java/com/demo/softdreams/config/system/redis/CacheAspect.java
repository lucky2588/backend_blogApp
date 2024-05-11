package com.demo.softdreams.config.system.redis;//package com.demo.softdreams.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.xmlbeans.XmlBeans;
//import org.aspectj.lang.JoinPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
//import javax.swing.text.Document;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Optional;
//
//@Aspect
//@Component
//public class CacheAspect {
//
//    @Autowired
//    private CacheManager cacheManager;

//    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void checkCache(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        if (methodName.equals("getData")) {
//            Optional<String> xmlDataOptional = Optional.ofNullable(cache.get("data").toString());
//            if (xmlDataOptional.isPresent()) {
//                String xmlData = xmlDataOptional.get();
//                ObjectMapper mapper = new ObjectMapper();
//                Document xmlDocument = XmlBeans.parseString(xmlData);
//                {
//}





//
//        @Around("@annotation(com.example.Cacheable)")
//        public Object cacheableAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//            // Lấy thông tin về phương thức và tham số
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            Method method = signature.getMethod();
//            Cacheable cacheableAnnotation = method.getAnnotation(Cacheable.class);
//            String cacheName = cacheableAnnotation.value().toString();
//            Object[] args = joinPoint.getArgs();
//            // Kiểm tra xem dữ liệu đã có trong cache chưa
//            String cacheKey = generateCacheKey(method, args);
//            Object data = getFromCache(cacheName, cacheKey);
//            if (data != null) {
//                // Nếu có trong cache, trả về dữ liệu từ cache
//                return convertDataToJson(data);
//            } else {
//                // Nếu không có trong cache, gọi phương thức và lưu vào cache
//                data = joinPoint.proceed();
//                saveToCache(cacheName, cacheKey, data);
//                return convertDataToJson(data);
//            }
//        }
//
//        private String generateCacheKey(Method method, Object[] args) {
//            // Tạo cache key từ tên phương thức và tham số
//            // Bạn có thể tuỳ chỉnh logic tạo cache key theo nhu cầu của bạn
//            // Ví dụ: sử dụng tên phương thức và các tham số của phương thức
//            return method.getName() + "_" + Arrays.toString(args);
//        }
//
//        private Object getFromCache(String cacheName, String cacheKey) {
//            // Lấy dữ liệu từ cache
//            // Thực hiện logic lấy dữ liệu từ cache, trả về null nếu không tìm thấy
//            // Đây là nơi bạn triển khai logic cụ thể cho việc lấy dữ liệu từ cache
//            return null;
//        }
//
//        private void saveToCache(String cacheName, String cacheKey, Object data) {
//            // Lưu dữ liệu vào cache
//            // Thực hiện logic lưu dữ liệu vào cache
//            // Đây là nơi bạn triển khai logic cụ thể cho việc lưu dữ liệu vào cache
//        }
//
//        private String convertDataToJson(Object data) {
//            // Chuyển đổi dữ liệu thành JSON
//            // Thực hiện logic chuyển đổi dữ liệu sang JSON
//            // Đây là nơi bạn triển khai logic cụ thể cho việc chuyển đổi dữ liệu sang JSON
//            return null;
//        }
//    }
//
//
//

