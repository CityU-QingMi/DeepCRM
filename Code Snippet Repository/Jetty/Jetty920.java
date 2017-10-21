    @Override
    public SessionData load(String id) throws Exception
    {
        if (LOG.isDebugEnabled()) LOG.debug("Loading session {} from DataStore", id);

        Entity entity = _datastore.get(makeKey(id, _context));
        if (entity == null)
        {
            if (LOG.isDebugEnabled()) LOG.debug("No session {} in DataStore ", id);
            return null;
        }
        else
        {
            SessionData data = sessionFromEntity(entity);
            return data;
        }
    }
