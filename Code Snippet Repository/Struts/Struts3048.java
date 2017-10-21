    public static void handleSetProperty(Object bean, String prop,
                                         Object value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{value});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
