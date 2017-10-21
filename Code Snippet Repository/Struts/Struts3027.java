    public static void handleSetProperty(Object bean, String prop,
                                         long value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Long(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
