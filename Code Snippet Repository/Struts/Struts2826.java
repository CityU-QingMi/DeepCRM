    public static String coerceToShort(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Short) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Short.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Short((short) 0)";
            } else {
                // Detect format error at translation time
                return "new Short(\"" + Short.valueOf(s).toString() + "\")";
            }
        }
    }
