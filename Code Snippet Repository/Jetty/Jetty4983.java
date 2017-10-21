    public void match (Pattern pattern, URI[] uris, boolean isNullInclusive)
    throws Exception
    {
        if (uris!=null)
        {
            String[] patterns = (pattern==null?null:pattern.pattern().split(","));

            List<Pattern> subPatterns = new ArrayList<Pattern>();
            for (int i=0; patterns!=null && i<patterns.length;i++)
            {
                subPatterns.add(Pattern.compile(patterns[i]));
            }
            if (subPatterns.isEmpty())
                subPatterns.add(pattern);

            if (subPatterns.isEmpty())
            {
                matchPatterns(null, uris, isNullInclusive);
            }
            else
            {
                //for each subpattern, iterate over all the urls, processing those that match
                for (Pattern p : subPatterns)
                {
                    matchPatterns(p, uris, isNullInclusive);
                }
            }
        }
    }
