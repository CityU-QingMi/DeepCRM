    public static boolean match(String pathSpec, String path, boolean noDefault)
    {
        if (pathSpec.length()==0)
            return "/".equals(path);
            
        char c = pathSpec.charAt(0);
        if (c=='/')
        {
            if (!noDefault && pathSpec.length()==1 || pathSpec.equals(path))
                return true;

            if(isPathWildcardMatch(pathSpec, path))
                return true;
        }
        else if (c=='*')
            return path.regionMatches(path.length()-pathSpec.length()+1,
                    pathSpec,1,pathSpec.length()-1);
        return false;
    }
