    private void reportBulkChanges (List<String> filenames)
    {
        Iterator<Listener> itor = _listeners.iterator();
        while (itor.hasNext())
        {
            Listener l = itor.next();
            try
            {
                if (l instanceof BulkListener)
                    ((BulkListener)l).filesChanged(filenames);
            }
            catch (Exception e)
            {
                warn(l,filenames.toString(),e);
            }
            catch (Error e)
            {
                warn(l,filenames.toString(),e);
            }
        }
    }
