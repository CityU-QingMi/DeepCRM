    private boolean parse()
    {
        while (true)
        {
            // Must parse even if the buffer is fully consumed, to allow the
            // parser to advance from asynchronous content to response complete.
            boolean handle = parser.parseNext(buffer);
            if (LOG.isDebugEnabled())
                LOG.debug("Parsed {}, remaining {} {}", handle, buffer.remaining(), parser);
            if (handle || !buffer.hasRemaining())
                return handle;
        }
    }
