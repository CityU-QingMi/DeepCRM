    protected void addConverterMapping(Map<String, Object> mapping, Class clazz) {
        // Process <clazz>-conversion.properties file
        String converterFilename = buildConverterFilename(clazz);
        fileProcessor.process(mapping, clazz, converterFilename);

        // Process annotations
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Conversion) {
                Conversion conversion = (Conversion) annotation;
                for (TypeConversion tc : conversion.conversions()) {
                    if (mapping.containsKey(tc.key())) {
                        break;
                    }
                    if (LOG.isDebugEnabled()) {
                        if (StringUtils.isEmpty(tc.key())) {
                            LOG.debug("WARNING! key of @TypeConversion [{}/{}] applied to [{}] is empty!", tc.converter(), tc.converterClass(), clazz.getName());
                        } else {
                            LOG.debug("TypeConversion [{}/{}] with key: [{}]", tc.converter(), tc.converterClass(), tc.key());
                        }
                    }
                    annotationProcessor.process(mapping, tc, tc.key());
                }
            }
        }

        // Process annotated methods
        for (Method method : clazz.getMethods()) {
            annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof TypeConversion) {
                    TypeConversion tc = (TypeConversion) annotation;
                    if (mapping.containsKey(tc.key())) {
                        break;
                    }
                    String key = tc.key();
                    // Default to the property name
                    if (StringUtils.isEmpty(key)) {
                        key = AnnotationUtils.resolvePropertyName(method);
                        LOG.debug("Retrieved key [{}] from method name [{}]", key, method.getName());
                    }
                    annotationProcessor.process(mapping, tc, key);
                }
            }
        }
    }
