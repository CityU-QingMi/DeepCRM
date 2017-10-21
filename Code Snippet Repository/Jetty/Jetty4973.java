    public void handleWatchEvent (Path path, PathWatchEvent event)
    {
        PathWatchEvent existing = pending.get(path);

        if (LOG.isDebugEnabled())
            LOG.debug("handleWatchEvent {} {} <= {}", path, event, existing);
        
        switch(event.getType())
        {
            case ADDED:
                if (existing!=null && existing.getType()==PathWatchEventType.MODIFIED)
                    events.add(new PathWatchEvent(path,PathWatchEventType.DELETED,existing.getConfig()));
                pending.put(path,event);
                break;
                
            case MODIFIED:
                if (existing==null)
                    pending.put(path,event);
                else
                    existing.modified();
                break;
                
            case DELETED:
            case UNKNOWN:
                if (existing!=null)
                    pending.remove(path);
                events.add(event);
                break;
                
        }
    }
