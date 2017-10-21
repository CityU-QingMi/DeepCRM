    public static String coerceToByte(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Byte) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Byte.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Byte((byte) 0)";
            } else {
                // Detect format error at translation time
                return "new Byte((byte)" + Byte.valueOf(s).toString() + ")";
            }
        }
    }
