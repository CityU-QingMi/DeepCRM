    @Override
    public boolean matches(String path)
    {
        switch (group)
        {
            case EXACT:
                return pathSpec.equals(path);
            case PREFIX_GLOB:
                return isWildcardMatch(path);
            case SUFFIX_GLOB:
                return path.regionMatches((path.length() - specLength) + 1,pathSpec,1,specLength - 1);
            case ROOT:
                // Only "/" matches
                return ("/".equals(path));
            case DEFAULT:
                // If we reached this point, then everything matches
                return true;
            default:
                return false;
        }
    }
