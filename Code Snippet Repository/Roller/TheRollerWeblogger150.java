    OAuthAccessor accessorFromRecord(OAuthAccessorRecord record) {
        OAuthAccessor accessor = null;
        if (record != null) {
            accessor =
                new OAuthAccessor(getConsumerByKey(record.getConsumerKey()));
            accessor.accessToken = record.getAccessToken();
            accessor.requestToken = record.getRequestToken();
            accessor.tokenSecret = record.getTokenSecret();
            if (record.getAuthorized() != null) {
                accessor.setProperty("authorized", record.getAuthorized());
            }
            if (record.getUserName() != null) {
                accessor.setProperty("userId", record.getUserName());
            }
        }
        return accessor;
    }
