    public static String coerceToInteger(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Integer) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Integer.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Integer(0)";
            } else {
                // Detect format error at translation time
                return "new Integer(" + Integer.valueOf(s).toString() + ")";
            }
        }
    }
