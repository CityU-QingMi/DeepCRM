    @Override
    public boolean check(String uri, Resource resource)
    {
        // Only support PathResource alias checking
        if (!(resource instanceof PathResource))
            return false;
        
        PathResource pathResource = (PathResource) resource;

        try
        {
            Path path = pathResource.getPath();
            Path alias = pathResource.getAliasPath();

            if (path.equals(alias))
                return false; // Unknown why this is an alias

            if (hasSymbolicLink(path) && Files.isSameFile(path, alias))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Allow symlink {} --> {}", resource, pathResource.getAliasPath());
                return true;
            }
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }
        
        return false;
    }
