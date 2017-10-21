    protected <T extends Annotation> T getAnnotation(Class parentClass, String property, Class<T> annotationClass) {
        T annotation = null;
        Field field = reflectionProvider.getField(parentClass, property);

        if (field != null) {
            annotation = field.getAnnotation(annotationClass);
        }
        if (annotation == null) { // HINT: try with setter
            annotation = getAnnotationFromSetter(parentClass, property, annotationClass);
        }
        if (annotation == null) { // HINT: try with getter
            annotation = getAnnotationFromGetter(parentClass, property, annotationClass);
        }

        return annotation;
    }
