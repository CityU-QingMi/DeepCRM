    private boolean advance(Iterator<ByteBuffer> iterator)
    {
        boolean hasNext = iterator.hasNext();
        ByteBuffer bytes = hasNext ? iterator.next() : null;
        boolean hasMore = hasNext && iterator.hasNext();
        boolean wasLast = last;
        last = !hasMore;

        if (hasNext)
        {
            buffer = bytes;
            content = bytes == null ? null : bytes.slice();
            if (LOG.isDebugEnabled())
                LOG.debug("Advanced content to {} chunk {}", hasMore ? "next" : "last", String.valueOf(bytes));
            return bytes != null;
        }
        else
        {
            // No more content, but distinguish between last and consumed.
            if (wasLast)
            {
                buffer = content = AFTER;
                if (LOG.isDebugEnabled())
                    LOG.debug("Advanced content past last chunk");
            }
            else
            {
                buffer = content = CLOSE;
                if (LOG.isDebugEnabled())
                    LOG.debug("Advanced content to last chunk");
            }
            return false;
        }
    }
