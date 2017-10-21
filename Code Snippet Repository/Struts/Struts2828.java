    public static String coerceToLong(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Long) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Long.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Long(0)";
            } else {
                // Detect format error at translation time
                return "new Long(" + Long.valueOf(s).toString() + "l)";
            }
        }
    }
