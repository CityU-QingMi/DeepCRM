    public static Object coerce(String s, Class target) {

        boolean isNullOrEmpty = (s == null || s.length() == 0);

        if (target == Boolean.class) {
            if (isNullOrEmpty) {
                s = "false";
            }
            return new Boolean(s);
        } else if (target == Byte.class) {
            if (isNullOrEmpty)
                return new Byte((byte) 0);
            else
                return new Byte(s);
        } else if (target == Character.class) {
            if (isNullOrEmpty)
                return new Character((char) 0);
            else
                return new Character(s.charAt(0));
        } else if (target == Double.class) {
            if (isNullOrEmpty)
                return new Double(0);
            else
                return new Double(s);
        } else if (target == Float.class) {
            if (isNullOrEmpty)
                return new Float(0);
            else
                return new Float(s);
        } else if (target == Integer.class) {
            if (isNullOrEmpty)
                return new Integer(0);
            else
                return new Integer(s);
        } else if (target == Short.class) {
            if (isNullOrEmpty)
                return new Short((short) 0);
            else
                return new Short(s);
        } else if (target == Long.class) {
            if (isNullOrEmpty)
                return new Long(0);
            else
                return new Long(s);
        } else {
            return null;
        }
    }
