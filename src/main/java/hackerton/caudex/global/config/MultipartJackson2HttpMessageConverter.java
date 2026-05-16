package hackerton.caudex.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MultipartJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public MultipartJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    // 1. 제네릭 타입이나 컬렉션 형태의 octet-stream이 들어와도 Jackson이 읽을 수 있다고 허용
    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        if (MediaType.APPLICATION_OCTET_STREAM.equals(mediaType)) {
            return true;
        }
        return super.canRead(type, contextClass, mediaType);
    }

    // 2. 일반 클래스 타입의 octet-stream이 들어와도 Jackson이 읽을 수 있다고 허용
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        if (MediaType.APPLICATION_OCTET_STREAM.equals(mediaType)) {
            return true;
        }
        return super.canRead(clazz, mediaType);
    }
}