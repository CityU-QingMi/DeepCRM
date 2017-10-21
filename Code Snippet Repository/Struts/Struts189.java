    private <T extends Annotation> T getAnnotationFromSetter(Class parentClass, String property, Class<T> annotationClass) {
        try {
            Method setter = reflectionProvider.getSetMethod(parentClass, property);

            if (setter != null) {
                return setter.getAnnotation(annotationClass);
            }
        } catch (ReflectionException | IntrospectionException e) {
            // ignore
        }
        return null;
    }
