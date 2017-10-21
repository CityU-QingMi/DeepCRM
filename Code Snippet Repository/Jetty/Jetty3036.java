    public boolean checkAlias(String path, Resource resource)
    {
        // Is the resource aliased?
        if (resource.isAlias())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Aliased resource: " + resource + "~=" + resource.getAlias());

            // alias checks
            for (Iterator<AliasCheck> i=_aliasChecks.iterator();i.hasNext();)
            {
                AliasCheck check = i.next();
                if (check.check(path,resource))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Aliased resource: " + resource + " approved by " + check);
                    return true;
                }
            }
            return false;
        }
        return true;
    }
