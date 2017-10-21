    public void sendContent(HttpContent content) throws IOException
    {
        try (Blocker blocker = _writeBlocker.acquire())
        {
            sendContent(content, blocker);
            blocker.block();
        }
        catch (Throwable failure)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(failure);
            abort(failure);
            throw failure;
        }
    }
