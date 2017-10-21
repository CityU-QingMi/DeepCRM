    public static String coerceToDouble(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Double) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Double.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Double(0)";
            } else {
                // Detect format error at translation time
                return "new Double(" + Double.valueOf(s).toString() + ")";
            }
        }
    }
