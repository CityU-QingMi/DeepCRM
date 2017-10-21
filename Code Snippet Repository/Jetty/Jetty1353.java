    public static String pathMatch(String pathSpec, String path)
    {
        char c = pathSpec.charAt(0);

        if (c=='/')
        {
            if (pathSpec.length()==1)
                return path;

            if (pathSpec.equals(path))
                return path;

            if (isPathWildcardMatch(pathSpec, path))
                return path.substring(0,pathSpec.length()-2);
        }
        else if (c=='*')
        {
            if (path.regionMatches(path.length()-(pathSpec.length()-1),
                    pathSpec,1,pathSpec.length()-1))
                return path;
        }
        return null;
    }
