    public static String coerceToBoolean(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Boolean) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Boolean.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Boolean(false)";
            } else {
                // Detect format error at translation time
                return "new Boolean(" + Boolean.valueOf(s).toString() + ")";
            }
        }
    }
