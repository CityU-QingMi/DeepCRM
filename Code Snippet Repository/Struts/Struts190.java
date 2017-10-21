    private Class getClass(Class parentClass, String property, boolean element) {
        try {
            Field field = reflectionProvider.getField(parentClass, property);
            Type genericType = null;
            // Check fields first
            if (field != null) {
                genericType = field.getGenericType();
            }
            // Try to get ParameterType from setter method
            if (genericType == null || !(genericType instanceof ParameterizedType)) {
                try {
                    Method setter = reflectionProvider.getSetMethod(parentClass, property);
                    genericType = setter != null ? setter.getGenericParameterTypes()[0] : null;
                } catch (ReflectionException | IntrospectionException e) {
                    // ignore
                }
            }

            // Try to get ReturnType from getter method
            if (genericType == null || !(genericType instanceof ParameterizedType)) {
                try {
                    Method getter = reflectionProvider.getGetMethod(parentClass, property);
                    genericType = getter.getGenericReturnType();
                } catch (ReflectionException | IntrospectionException e) {
                    // ignore
                }
            }

            if (genericType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) genericType;
                int index = (element && type.getRawType().toString().contains(Map.class.getName())) ? 1 : 0;
                Type resultType = type.getActualTypeArguments()[index];
                if (resultType instanceof ParameterizedType) {
                    return (Class) ((ParameterizedType) resultType).getRawType();
                }
                return (Class) resultType;
            }
        } catch (Exception e) {
            LOG.debug("Error while retrieving generic property class for property: {}", property, e);
        }
        return null;
    }
