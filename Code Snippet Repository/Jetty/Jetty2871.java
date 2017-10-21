    private void write(ByteBuffer content, boolean complete) throws IOException
    {
        try (Blocker blocker = _writeBlocker.acquire())
        {
            write(content, complete, blocker);
            blocker.block();
        }
        catch (Exception failure)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(failure);
            abort(failure);
            if (failure instanceof IOException)
                throw failure;
            throw new IOException(failure);
        }
    }
