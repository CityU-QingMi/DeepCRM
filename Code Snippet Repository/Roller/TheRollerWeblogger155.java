    public OAuthAccessor getAccessor(OAuthMessage requestMessage)
            throws IOException, OAuthProblemException {

        String consumerToken = requestMessage.getToken();
        OAuthAccessor accessor = null;
        if (StringUtils.isNotEmpty(consumerToken)) {
            // caller provided a token, it better be good or else
            accessor = getAccessorByToken(consumerToken);
            if (accessor == null) {
                throw new OAuthProblemException("token_expired");
            }
        }

        String consumerKey = requestMessage.getConsumerKey();
        if (accessor == null && StringUtils.isNotEmpty(consumerKey)) {
            // caller provided contumer key, do we have an accessor yet
            accessor = getAccessorByKey(consumerKey);
        }
        return accessor;
    }
