    public static String coerceToPrimitiveFloat(String s,
                                                boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToFloat(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "(float) 0";
            else
                return Float.valueOf(s).toString() + "f";
        }
    }
