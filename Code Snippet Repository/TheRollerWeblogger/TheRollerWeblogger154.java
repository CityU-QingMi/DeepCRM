    OAuthAccessor getAccessorByToken(String token) {
        OAuthAccessorRecord record = null;
        try {
            TypedQuery<OAuthAccessorRecord> q = strategy.getNamedQuery("OAuthAccessorRecord.getByToken",
                    OAuthAccessorRecord.class);
            q.setParameter(1, token);
            record = q.getSingleResult();

        } catch (Exception ex) {
            log.debug("ERROR fetching accessor", ex);
        }
        return accessorFromRecord(record);
    }
