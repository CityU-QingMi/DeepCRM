    public static void handleSetProperty(Object bean, String prop,
                                         double value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Double(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
