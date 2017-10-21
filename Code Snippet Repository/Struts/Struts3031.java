    public static void handleSetProperty(Object bean, String prop,
                                         byte value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Byte(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
