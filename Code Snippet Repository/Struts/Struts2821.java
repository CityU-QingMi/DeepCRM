    public static String coerceToFloat(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Float) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Float.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Float(0)";
            } else {
                // Detect format error at translation time
                return "new Float(" + Float.valueOf(s).toString() + "f)";
            }
        }
    }
