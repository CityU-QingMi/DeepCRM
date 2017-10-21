    @Override
    public void doStore(String id, SessionData data, long lastSaveTime) throws Exception
    {
        if (LOG.isDebugEnabled()) LOG.debug("Writing session {} to DataStore", data.getId());
        Entity entity = entityFromSession(data, makeKey(id, _context));

        //attempt the update with exponential back-off
        int backoff = getBackoffMs();
        int attempts;
        for (attempts = 0; attempts < getMaxRetries(); attempts++)
        {
            try
            {
                _datastore.put(entity);
                return;
            }
            catch (DatastoreException e)
            {
                if (e.isRetryable())
                {
                    if (LOG.isDebugEnabled()) LOG.debug("Datastore put retry {} waiting {}ms", attempts, backoff);
                        
                    try
                    {
                        Thread.currentThread().sleep(backoff);
                    }
                    catch (InterruptedException x)
                    {
                    }
                    backoff *= 2;
                }
                else
                {
                   throw e;
                }
            }
        }
        
        //retries have been exceeded
        throw new UnwriteableSessionDataException(id, _context, null);
    }
