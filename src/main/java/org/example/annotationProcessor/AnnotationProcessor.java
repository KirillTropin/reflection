package org.example.annotationProcessor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import org.example.annotations.CustomToString;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.InvocationHandler;
import java.util.Set;


@SupportedAnnotationTypes("org.example.annotations.CustomToString")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(CustomToString.class);
            annotatedElements.iterator().forEachRemaining(element -> {
                this.generate(element);
            });
        }
        return true;
    }


    public JavaFile generate(Element element) {
        TypeElement typeElement = (TypeElement) element;
        String packageName = element.getClass().getPackage().getName();
        String className = typeElement.getSimpleName() + "ProxyPattern";

        TypeSpec.Builder specBuilder = TypeSpec.classBuilder(className);
        specBuilder.addOriginatingElement(typeElement);
        specBuilder.addModifiers(Modifier.FINAL);
        specBuilder.addAnnotation(AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", getClass().getName())
                .build());
        specBuilder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .build());
        specBuilder.addMethod(MethodSpec.methodBuilder("customToString")
                .addCode(element.getSimpleName() + ".class:" + element.getClass().getMethods().toString().toUpperCase() + element.getClass().getDeclaredFields().toString().toUpperCase())
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .returns(String.class)
                .addParameter(ParameterSpec.builder(InvocationHandler.class, "HANDLER").build())
                .build());

        return JavaFile.builder(packageName, specBuilder.build())
                .skipJavaLangImports(true)
                .indent("    ")
                .build();
    }
}
