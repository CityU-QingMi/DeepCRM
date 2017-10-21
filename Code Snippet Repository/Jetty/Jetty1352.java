    private static boolean isPathWildcardMatch(String pathSpec, String path)
    {
        // For a spec of "/foo/*" match "/foo" , "/foo/..." but not "/foobar"
        int cpl=pathSpec.length()-2;
        if (pathSpec.endsWith("/*") && path.regionMatches(0,pathSpec,0,cpl))
        {
            if (path.length()==cpl || '/'==path.charAt(cpl))
                return true;
        }
        return false;
    }
