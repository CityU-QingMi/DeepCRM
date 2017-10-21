    public static final String decode(String s) {
        try {
            if (s != null) {
                return URLDecoder.decode(s, "UTF-8");
            } else {
                return s;
            }
        } catch (UnsupportedEncodingException e) {
            // Java Spec requires UTF-8 be in all Java environments, so this
            // should not happen
            return s;
        }
    }
