    private boolean isWildcardMatch(String path)
    {
        // For a spec of "/foo/*" match "/foo" , "/foo/..." but not "/foobar"
        int cpl = specLength - 2;
        if ((group == PathSpecGroup.PREFIX_GLOB) && (path.regionMatches(0,pathSpec,0,cpl)))
        {
            if ((path.length() == cpl) || ('/' == path.charAt(cpl)))
            {
                return true;
            }
        }
        return false;
    }
