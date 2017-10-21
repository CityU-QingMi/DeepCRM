    @Override
    public String getPathInfo(String path)
    {
        // Path Info only valid for PREFIX_GLOB types
        if (group == PathSpecGroup.PREFIX_GLOB)
        {
            if (path.length() == (specLength - 2))
            {
                return null;
            }
            return path.substring(specLength - 2);
        }

        return null;
    }
