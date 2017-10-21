    @Override
    public void destroy()
    {
        for (File file : _files)
        {
            if (file.exists())
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Destroy {}",file);
                IO.delete(file);
            }
        }
    }
