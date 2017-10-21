    public void matchPatterns (Pattern pattern, URI[] uris, boolean isNullInclusive)
    throws Exception
    {
        for (int i=0; i<uris.length;i++)
        {
            URI uri = uris[i];
            String s = uri.toString();
            if ((pattern == null && isNullInclusive)
                    ||
                    (pattern!=null && pattern.matcher(s).matches()))
            {
                matched(uris[i]);
            }
        }
    }
