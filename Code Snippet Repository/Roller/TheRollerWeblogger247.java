    private String regexEscape(String smiley) {
        char[] chars = smiley.toCharArray();
        StringBuilder buf = new StringBuilder();
        for (int i=0; i<chars.length; i++) {
            for (int x=0; x<escape_regex.length; x++) {
                if (escape_regex[x] == chars[i]) {
                    buf.append("\\");
                    break;
                }
            }
            buf.append(chars[i]);
        }
        return buf.toString();
    }
