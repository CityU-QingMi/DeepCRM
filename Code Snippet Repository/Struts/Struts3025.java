    public static void handleSetProperty(Object bean, String prop,
                                         int value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Integer(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
