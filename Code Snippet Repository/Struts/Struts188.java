    private <T extends Annotation> T getAnnotationFromGetter(Class parentClass, String property, Class<T> annotationClass) {
        try {
            Method getter = reflectionProvider.getGetMethod(parentClass, property);

            if (getter != null) {
                return getter.getAnnotation(annotationClass);
            }
        } catch (ReflectionException | IntrospectionException e) {
            // ignore
        }
        return null;
    }
