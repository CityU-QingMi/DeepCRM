    public static String coerceToPrimitiveBoolean(String s,
                                                  boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToBoolean(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "false";
            else
                return Boolean.valueOf(s).toString();
        }
    }
