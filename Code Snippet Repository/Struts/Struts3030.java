    public static void handleSetProperty(Object bean, String prop,
                                         char value)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{new Character(value)});
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
