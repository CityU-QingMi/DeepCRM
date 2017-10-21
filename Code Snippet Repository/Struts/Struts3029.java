    public static void handleSetProperty(Object bean, String prop,
                                         float value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Float(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
