    public static String coerceToPrimitiveByte(String s,
                                               boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerceToByte(" + s + ")";
        } else {
            if (s == null || s.length() == 0)
                return "(byte) 0";
            else
                return "((byte)" + Byte.valueOf(s).toString() + ")";
        }
    }
