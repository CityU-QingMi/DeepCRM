    private String htmlEscape(String smiley) {
        char[] chars = smiley.toCharArray();
        StringBuilder buf = new StringBuilder();
        for (int i=0; i<chars.length; i++) {
            if (chars[i] == '"') {
                buf.append("&quot;");
            } else if (chars[i] == '>') {
                buf.append("&gt;");
            } else if (chars[i] == '<') {
                buf.append("&lt;");
            } else {
                buf.append(chars[i]);
            }
        }
        return buf.toString();
    }
