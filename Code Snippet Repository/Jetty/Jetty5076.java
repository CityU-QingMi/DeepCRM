    public static boolean equalsIgnoreEncodings(URI uriA, URI uriB)
    {
        if (uriA.equals(uriB))
            return true;

        if (uriA.getScheme()==null)
        {
            if (uriB.getScheme()!=null)
                return false;
        }
        else if (!uriA.getScheme().equals(uriB.getScheme()))
            return false;

        if (uriA.getAuthority()==null)
        {
            if (uriB.getAuthority()!=null)
                return false;
        }
        else if (!uriA.getAuthority().equals(uriB.getAuthority()))
            return false;

        return equalsIgnoreEncodings(uriA.getPath(),uriB.getPath());
    }
