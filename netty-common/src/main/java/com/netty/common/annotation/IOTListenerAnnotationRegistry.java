package com.netty.common.annotation;

import com.netty.common.constant.CommandMapping;
import com.netty.common.constant.HandleInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class IOTListenerAnnotationRegistry {

    public IOTListenerAnnotationRegistry() {
        init();
    }

    public static void init() {
        if(!CommandMapping.getInitCompleted()){
            synchronized (CommandMapping.class){
                try {
                    List<MetadataReader> metadataReaders = scanPackage();
                    metadataReaders.stream().filter(metadataReader -> {
                        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                        return annotationMetadata.hasAnnotation(Message.class.getName())||
                                annotationMetadata.hasAnnotation(Encoder.class.getName())||
                                annotationMetadata.hasAnnotation(Decoder.class.getName())||
                                annotationMetadata.hasAnnotatedMethods(Handler.class.getName());
                    }).forEach(metadataReader -> {
                        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                        if(annotationMetadata.hasAnnotation(Message.class.getName())){
                            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Message.class.getName());
                            CommandMapping.registryMessage((Byte)annotationAttributes.get("value"),annotationMetadata.getClassName());
                        }
                        if(annotationMetadata.hasAnnotation(Decoder.class.getName())){
                            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Decoder.class.getName());
                            CommandMapping.registryDecoder((Byte)annotationAttributes.get("value"),annotationMetadata.getClassName());
                        }
                        if(annotationMetadata.hasAnnotation(Encoder.class.getName())){
                            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Encoder.class.getName());
                            CommandMapping.registryEncoder((Byte)annotationAttributes.get("value"),annotationMetadata.getClassName());
                        }
                        if(annotationMetadata.hasAnnotatedMethods(Handler.class.getName())){
                            String className = annotationMetadata.getClassName();
                            Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(Handler.class.getName());
                            for(MethodMetadata methodMetadata:annotatedMethods){
                                HandleInfo handleInfo = new HandleInfo(className,methodMetadata.getMethodName());
                                Map<String, Object> annotationAttributes = methodMetadata.getAnnotationAttributes(Handler.class.getName());
                                CommandMapping.registryHandle((Byte)annotationAttributes.get("value"),handleInfo);
                            }
                        }
                    });
                    log.info("IOTListenerAnnotationRegistry init completed");
                    CommandMapping.setInitCompleted(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private static List<MetadataReader> scanPackage() throws Exception{
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(null);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory();
        Resource[] resources = resolver.getResources("classpath*:com/netty/**/*.class");
        List<MetadataReader> readers = new ArrayList<MetadataReader>();
        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            readers.add(reader);
        }
        return readers;
    }
}
