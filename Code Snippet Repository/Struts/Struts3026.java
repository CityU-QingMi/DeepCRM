    public static void handleSetProperty(Object bean, String prop,
                                         short value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Short(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
