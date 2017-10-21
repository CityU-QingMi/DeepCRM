    private String getPageEncodingFromDirective(Attributes attrs,
            String attrName) {
        String value = attrs.getValue(attrName);
        if (attrName.equals("pageEncoding")) {
            return value;
        }

        // attrName = contentType
        String contentType = value;
        String encoding = null;
        if (contentType != null) {
            int loc = contentType.indexOf(CHARSET);
            if (loc != -1) {
                encoding = contentType.substring(loc + CHARSET.length());
            }
        }

        return encoding;
    }
