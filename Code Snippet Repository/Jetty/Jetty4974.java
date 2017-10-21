    private long processPending()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("processPending> {}",pending.values());
        
        long now = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        long wait = Long.MAX_VALUE;
        
        // pending map is maintained in LRU order
        for (PathWatchEvent event : new ArrayList<>(pending.values()))
        {
            Path path = event.getPath();
            // for directories, wait until parent is quiet
            if (pending.containsKey(path.getParent()))
                continue;

            // if the path is quiet move to events
            if (event.isQuiet(now,getUpdateQuietTimeMillis()))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("isQuiet {}",event);
                pending.remove(path);
                events.add(event);
            }
            else
            {
                long ms_to_check = event.toQuietCheck(now,getUpdateQuietTimeMillis());
                if (LOG.isDebugEnabled())
                    LOG.debug("pending {} {}",event, ms_to_check);
                if (ms_to_check<wait)
                    wait = ms_to_check;
            }
        }
        if (LOG.isDebugEnabled())
            LOG.debug("processPending< {}",pending.values());
        return wait==Long.MAX_VALUE?-1:wait;
    }
