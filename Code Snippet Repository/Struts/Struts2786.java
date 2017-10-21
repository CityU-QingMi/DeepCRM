    public void startPrefixMapping(String prefix, String uri)
        throws SAXException {
        TagLibraryInfo taglibInfo;

        if (directivesOnly && !(JSP_URI.equals(uri))) {
            return;
        }
        
        try {
            taglibInfo = getTaglibInfo(prefix, uri);
        } catch (JasperException je) {
            throw new SAXParseException(
                Localizer.getMessage("jsp.error.could.not.add.taglibraries"),
                locator,
                je);
        }

        if (taglibInfo != null) {
            if (pageInfo.getTaglib(uri) == null) {
                pageInfo.addTaglib(uri, taglibInfo);
            }
            pageInfo.pushPrefixMapping(prefix, uri);
        } else {
            pageInfo.pushPrefixMapping(prefix, null);
        }
    }
