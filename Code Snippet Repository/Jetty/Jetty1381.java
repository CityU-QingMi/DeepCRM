    @Override
    public String getPathMatch(String path)
    {
        switch (group)
        {
            case EXACT:
                if (pathSpec.equals(path))
                {
                    return path;
                }
                else
                {
                    return null;
                }
            case PREFIX_GLOB:
                if (isWildcardMatch(path))
                {
                    return path.substring(0,specLength - 2);
                }
                else
                {
                    return null;
                }
            case SUFFIX_GLOB:
                if (path.regionMatches(path.length() - (specLength - 1),pathSpec,1,specLength - 1))
                {
                    return path;
                }
                else
                {
                    return null;
                }
            case DEFAULT:
                return path;
            default:
                return null;
        }
    }
