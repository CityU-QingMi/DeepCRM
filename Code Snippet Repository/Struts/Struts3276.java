    @SuppressWarnings("")
    public void populateObject(Object object, final Map elements) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, IntrospectionException,
            IllegalArgumentException, JSONException, InstantiationException {
        Class clazz = object.getClass();

        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] props = info.getPropertyDescriptors();

        // iterate over class fields
        for (PropertyDescriptor prop : props) {
            String name = prop.getName();

            if (elements.containsKey(name)) {
                Object value = elements.get(name);
                Method method = prop.getWriteMethod();

                if (method != null) {
                    JSON json = method.getAnnotation(JSON.class);
                    if ((json != null) && !json.deserialize()) {
                        continue;
                    }

                    // use only public setters
                    if (Modifier.isPublic(method.getModifiers())) {
                        Class[] paramTypes = method.getParameterTypes();
                        Type[] genericTypes = method.getGenericParameterTypes();

                        if (paramTypes.length == 1) {
                            Object convertedValue = this.convert(paramTypes[0], genericTypes[0], value, method);
                            method.invoke(object, new Object[] { convertedValue });
                        }
                    }
                }
            }
        }
    }
