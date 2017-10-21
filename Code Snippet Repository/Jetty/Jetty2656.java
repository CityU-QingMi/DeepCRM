    @Override
    public void onPathWatchEvent(PathWatchEvent event)
    {
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug( "PATH WATCH EVENT: {}", event.getType() );
            }
            loadUsers();
        }
        catch (IOException e)
        {
            LOG.warn(e);
        }
    }
