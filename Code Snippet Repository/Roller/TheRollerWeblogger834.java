    public static String stripJsessionId(String url) {
        // Strip off jsessionid found in referer URL
        int startPos = url.indexOf(";jsessionid=");
        if (startPos != -1) {
            int endPos = url.indexOf('?', startPos);
            if (endPos == -1) {
                url = url.substring(0, startPos);
            } else {
                url = url.substring(0, startPos)
                        + url.substring(endPos, url.length());
            }
        }
        return url;
    }
