    private void checkPrefix(String uri, String qName) {

        String prefix = getPrefix(qName);
        if (prefix.length() > 0) {
            pageInfo.addPrefix(prefix);
            if ("jsp".equals(prefix) && !JSP_URI.equals(uri)) {
                pageInfo.setIsJspPrefixHijacked(true);
            }
        }
    }
