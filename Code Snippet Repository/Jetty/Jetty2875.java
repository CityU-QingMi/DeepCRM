    public void sendContent(InputStream in) throws IOException
    {
        try (Blocker blocker = _writeBlocker.acquire())
        {
            new InputStreamWritingCB(in, blocker).iterate();
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
