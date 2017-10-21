    public Set<String> getResourcePaths(String path)
    {
        try
        {
            path = URIUtil.canonicalPath(path);
            Resource resource = getResource(path);

            if (resource != null && resource.exists())
            {
                if (!path.endsWith(URIUtil.SLASH))
                    path = path + URIUtil.SLASH;

                String[] l = resource.list();
                if (l != null)
                {
                    HashSet<String> set = new HashSet<String>();
                    for (int i = 0; i < l.length; i++)
                        set.add(path + l[i]);
                    return set;
                }
            }
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }
        return Collections.emptySet();
    }
