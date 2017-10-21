    public boolean addComponent(File path)
    {
        StartLog.debug("Adding classpath component: %s",path);
        if ((path == null) || (!path.exists()))
        {
            // not a valid component
            return false;
        }

        try
        {
            File key = path.getCanonicalFile();
            if (!elements.contains(key))
            {
                elements.add(key);
                return true;
            }
        }
        catch (IOException e)
        {
            StartLog.debug(e);
        }

        return false;
    }
