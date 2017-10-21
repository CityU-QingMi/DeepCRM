    public static final String encode(String s) {
        try {
            if (s != null) {
                return URLEncoder.encode(s, "UTF-8");
            } else {
                return s;
            }
        } catch (UnsupportedEncodingException e) {
            // Java Spec requires UTF-8 be in all Java environments, so this
            // should not happen
            return s;
        }
    }
