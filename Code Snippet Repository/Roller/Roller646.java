    public void generateAccessToken(OAuthAccessor accessor)
            throws OAuthException {

        try {
            // generate oauth_token and oauth_secret
            // generate token and secret based on consumer_key
            String consumer_key = accessor.consumer.consumerKey;

            OAuthAccessorRecord record = (OAuthAccessorRecord) strategy.load(
                OAuthAccessorRecord.class, accessor.consumer.consumerKey);
            
            // for now use md5 of name + current time as token
            String token_data = consumer_key + System.nanoTime();
            String token = DigestUtils.md5Hex(token_data);

            record.setRequestToken(null);
            record.setAccessToken(token);
            strategy.store(record);

        } catch (WebloggerException ex) {
            throw new OAuthException("ERROR: generating access token", ex);
        }
    }
