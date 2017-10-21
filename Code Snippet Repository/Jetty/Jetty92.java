    private boolean isValidClassFilePath (String path)
    {
        //no path is not valid
        if (path == null || path.length()==0)
            return false;

        // skip any classfiles that are in a hidden directory
        if (path.startsWith(".") || path.contains("/."))
        {
            if (LOG.isDebugEnabled()) LOG.debug("Contains hidden dirs: " + path);
            return false;
        }

        return true;
    }
