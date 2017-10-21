    public static String pathInfo(String pathSpec, String path)
    {
        if ("".equals(pathSpec))
            return path; //servlet 3 spec sec 12.2 will be '/'

        char c = pathSpec.charAt(0);

        if (c=='/')
        {
            if (pathSpec.length()==1)
                return null;

            boolean wildcard = isPathWildcardMatch(pathSpec, path);

            // handle the case where pathSpec uses a wildcard and path info is "/*"
            if (pathSpec.equals(path) && !wildcard)
                return null;

            if (wildcard)
            {
                if (path.length()==pathSpec.length()-2)
                    return null;
                return path.substring(pathSpec.length()-2);
            }
        }
        return null;
    }
