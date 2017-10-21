    public Map<String, Object> getBeanMap(final Object source) throws IntrospectionException, OgnlException {
        Map<String, Object> beanMap = new HashMap<>();
        final Map sourceMap = createDefaultContext(source, null);
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(source);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            final String propertyName = propertyDescriptor.getDisplayName();
            Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod != null) {
                final Object value = compileAndExecute(propertyName, null, new OgnlTask<Object>() {
                    public Object execute(Object expr) throws OgnlException {
                        return Ognl.getValue(expr, sourceMap, source);
                    }
                });
                beanMap.put(propertyName, value);
            } else {
                beanMap.put(propertyName, "There is no read method for " + propertyName);
            }
        }
        return beanMap;
    }
