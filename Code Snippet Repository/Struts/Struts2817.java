    public static String coerceToCharacter(String s, boolean isNamedAttribute) {
        if (isNamedAttribute) {
            return "(Character) org.apache.struts2.jasper.runtime.JspRuntimeLibrary.coerce(" + s + ", Character.class)";
        } else {
            if (s == null || s.length() == 0) {
                return "new Character((char) 0)";
            } else {
                char ch = s.charAt(0);
                // this trick avoids escaping issues
                return "new Character((char) " + (int) ch + ")";
            }
        }
    }
