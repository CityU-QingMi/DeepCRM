    public static String coerceToPrimitiveLong(String s,
                                               boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToLong(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "(long) 0";
            else
                return Long.valueOf(s).toString() + "l";
        }
    }
