    public static String coerceToPrimitiveShort(String s,
                                                boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToShort(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "(short) 0";
            else
                return "((short) " + Short.valueOf(s).toString() + ")";
        }
    }
