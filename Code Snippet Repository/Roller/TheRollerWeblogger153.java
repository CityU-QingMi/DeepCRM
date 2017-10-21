    OAuthAccessor getAccessorByKey(String consumerKey) {
        OAuthAccessorRecord record = null;
        try {
            TypedQuery<OAuthAccessorRecord> q = strategy.getNamedQuery("OAuthAccessorRecord.getByKey",
                    OAuthAccessorRecord.class);
            q.setParameter(1, consumerKey);
            record = q.getSingleResult();

        } catch (Exception ex) {
            log.debug("ERROR fetching accessor", ex);
        }
        return accessorFromRecord(record);
    }
