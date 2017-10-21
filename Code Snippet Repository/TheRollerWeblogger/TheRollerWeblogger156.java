    public void markAsAuthorized(OAuthAccessor accessor, String userId)
            throws OAuthException {
        try {
            OAuthAccessorRecord record = (OAuthAccessorRecord) strategy.load(
                OAuthAccessorRecord.class, accessor.consumer.consumerKey);
            record.setUserName(userId);
            record.setAuthorized(Boolean.TRUE);
            strategy.store(record);
            
        } catch (WebloggerException ex) {
            throw new OAuthException("ERROR: setting authorization flag", ex);
        }
    }
