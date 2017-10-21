    public static Object convert(String propertyName, String s, Class t,
                                 Class propertyEditorClass)
            throws JasperException {
        try {
            if (s == null) {
                if (t.equals(Boolean.class) || t.equals(Boolean.TYPE))
                    s = "false";
                else
                    return null;
            }
            if (propertyEditorClass != null) {
                return getValueFromBeanInfoPropertyEditor(
                        t, propertyName, s, propertyEditorClass);
            } else if (t.equals(Boolean.class) || t.equals(Boolean.TYPE)) {
                if (s.equalsIgnoreCase("on") || s.equalsIgnoreCase("true"))
                    s = "true";
                else
                    s = "false";
                return new Boolean(s);
            } else if (t.equals(Byte.class) || t.equals(Byte.TYPE)) {
                return new Byte(s);
            } else if (t.equals(Character.class) || t.equals(Character.TYPE)) {
                return s.length() > 0 ? new Character(s.charAt(0)) : null;
            } else if (t.equals(Short.class) || t.equals(Short.TYPE)) {
                return new Short(s);
            } else if (t.equals(Integer.class) || t.equals(Integer.TYPE)) {
                return new Integer(s);
            } else if (t.equals(Float.class) || t.equals(Float.TYPE)) {
                return new Float(s);
            } else if (t.equals(Long.class) || t.equals(Long.TYPE)) {
                return new Long(s);
            } else if (t.equals(Double.class) || t.equals(Double.TYPE)) {
                return new Double(s);
            } else if (t.equals(String.class)) {
                return s;
            } else if (t.equals(java.io.File.class)) {
                return new java.io.File(s);
            } else if (t.getName().equals("java.lang.Object")) {
                return new Object[]{s};
            } else {
                return getValueFromPropertyEditorManager(
                        t, propertyName, s);
            }
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
