    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException {
        for (int i = 0; i < Constants.CACHED_DTD_PUBLIC_IDS.length; i++) {
            String cachedDtdPublicId = Constants.CACHED_DTD_PUBLIC_IDS[i];
            if (cachedDtdPublicId.equals(publicId)) {
                String resourcePath = Constants.CACHED_DTD_RESOURCE_PATHS[i];
                InputStream input = this.getClass().getResourceAsStream(
                        resourcePath);
                if (input == null) {
                    throw new SAXException(Localizer.getMessage(
                            "jsp.error.internal.filenotfound", resourcePath));
                }
                InputSource isrc = new InputSource(input);
                return isrc;
            }
        }
        Log log = LogFactory.getLog(MyEntityResolver.class);
        if (log.isDebugEnabled())
            log.debug("Resolve entity failed" + publicId + " " + systemId);
        log.error(Localizer.getMessage("jsp.error.parse.xml.invalidPublicId",
                publicId));
        return null;
    }
