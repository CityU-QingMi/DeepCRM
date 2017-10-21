    void addAccessor(OAuthAccessor accessor) throws OAuthException {

        OAuthAccessorRecord record = new OAuthAccessorRecord();
        record.setConsumerKey(accessor.consumer.consumerKey);
        record.setRequestToken(accessor.requestToken);
        record.setAccessToken(accessor.accessToken);
        record.setTokenSecret(accessor.tokenSecret);
        if (accessor.getProperty("userId") != null) {
            record.setUserName((String)accessor.getProperty("userId"));
        }

        if (record.getCreated() != null) {
            record.setCreated(record.getCreated());
        } else {
            record.setCreated(new Timestamp(new Date().getTime()));
        }
        
        if (record.getUpdated() != null) {
            record.setUpdated(record.getUpdated());
        } else {
            record.setUpdated(record.getCreated());
        }

        if (accessor.getProperty("authorized") != null) {
            record.setAuthorized((Boolean)accessor.getProperty("authorized"));
        }
        try {
            strategy.store(record);
        } catch (WebloggerException ex) {
            throw new OAuthException("ERROR storing accessor", ex);
        }
    }
