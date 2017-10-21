    public boolean prepend(Entry entry)
    {
        Throwable closed;
        synchronized (this)
        {
            closed = terminated;
            if (closed == null)
            {
                frames.offerFirst(entry);
                if (LOG.isDebugEnabled())
                    LOG.debug("Prepended {}, frames={}", entry, frames.size());
            }
        }
        if (closed == null)
            return true;
        closed(entry, closed);
        return false;
    }
