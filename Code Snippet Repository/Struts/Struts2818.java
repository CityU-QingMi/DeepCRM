    public static String coerceToPrimitiveDouble(String s,
                                                 boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToDouble(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "(double) 0";
            else
                return Double.valueOf(s).toString();
        }
    }
