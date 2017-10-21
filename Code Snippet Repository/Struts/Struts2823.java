    public static String coerceToInt(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToInt(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "0";
            else
                return Integer.valueOf(s).toString();
        }
    }
