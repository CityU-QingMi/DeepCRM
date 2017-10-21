    public static <T extends Annotation> T findAnnotation(Class<?> clazz, Class<T> annotationClass) {
        T ann = clazz.getAnnotation(annotationClass);
        while (ann == null && clazz != null) {
            ann = clazz.getAnnotation(annotationClass);
            if (ann == null) {
                ann = clazz.getPackage().getAnnotation(annotationClass);
            }
            if (ann == null) {
                clazz = clazz.getSuperclass();
                if (clazz != null) {
                    ann = clazz.getAnnotation(annotationClass);
                }
            }
        }

        return ann;
    }
