    public LinkbackExtractor(String refererURL, String requestURL) throws IOException {
        try {
            extractByParsingHtml(refererURL, requestURL);
            if (mRssLink != null) {
                extractByParsingRss(mRssLink, requestURL);
            }
        } catch (Exception e) {
            if (mLogger.isDebugEnabled()) {
                mLogger.debug("Extracting linkback", e);
            }
        }
    }
