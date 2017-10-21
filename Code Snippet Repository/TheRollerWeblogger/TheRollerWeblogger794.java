    public static final String replaceAllNoRegex(String source, String search, String replace) {
        StringBuilder buffer = new StringBuilder();
        if (source != null) {
            if (search.length() == 0) {
                return source;
            }
            int oldPos, pos;
            for (oldPos = 0, pos = source.indexOf(search, oldPos); pos != -1; oldPos = pos + search.length(),
                    pos = source.indexOf(search, oldPos)) {
                buffer.append(source.substring(oldPos, pos));
                buffer.append(replace);
            }
            if (oldPos < source.length()) {
                buffer.append(source.substring(oldPos));
            }
        }
        return new String(buffer);
    }
