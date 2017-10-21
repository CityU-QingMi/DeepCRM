    public void endPrefixMapping(String prefix) throws SAXException {

        if (directivesOnly) {
            String uri = pageInfo.getURI(prefix);
            if (!JSP_URI.equals(uri)) {
                return;
            }
        }

        pageInfo.popPrefixMapping(prefix);
    }
