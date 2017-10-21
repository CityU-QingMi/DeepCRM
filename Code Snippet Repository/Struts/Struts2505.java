    private Set<String> getAllowedMethods(Class<?> actionClass) {
        AllowedMethods annotation = AnnotationUtils.findAnnotation(actionClass, AllowedMethods.class);
        if (annotation == null) {
            return Collections.emptySet();
        } else {
            Set<String> methods = new HashSet<>();
            for (String method : annotation.value()) {
                methods.add(method);
            }
            return methods;
        }
    }
