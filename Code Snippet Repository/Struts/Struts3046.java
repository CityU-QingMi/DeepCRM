    public static Object handleGetProperty(Object o, String prop)
            throws JasperException {
        if (o == null) {
            throw new JasperException(
                    Localizer.getMessage("jsp.error.beans.nullbean"));
        }
        Object value = null;
        try {
            Method method = getReadMethod(o.getClass(), prop);
            value = method.invoke(o, (Object[]) null);
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
        return value;
    }
