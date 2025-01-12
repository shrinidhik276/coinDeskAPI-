package com.mystore.RetryFailedCases;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import  com.mystore.RetryFailedCases.RetryAnalyzer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

}
