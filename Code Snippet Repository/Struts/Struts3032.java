    public static void handleSetProperty(Object bean, String prop,
                                         boolean value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Boolean(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
