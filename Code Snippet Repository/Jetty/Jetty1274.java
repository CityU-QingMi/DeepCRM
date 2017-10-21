    public static boolean tagEquals(String etag, String tag)
    {
        if (etag.equals(tag))
            return true;

        int dashdash = tag.indexOf("--");
        if (dashdash>0 && dashdash==etag.length()-1)
            return etag.regionMatches(0,tag,0,dashdash);
        return false;
    }
