    private boolean originMatches(List<String> allowedOrigins, String originList)
    {
        if (originList.trim().length() == 0)
            return false;

        String[] origins = originList.split(" ");
        for (String origin : origins)
        {
            if (origin.trim().length() == 0)
                continue;

            for (String allowedOrigin : allowedOrigins)
            {
                if (allowedOrigin.contains("*"))
                {
                    Matcher matcher = createMatcher(origin, allowedOrigin);
                    if (matcher.matches())
                        return true;
                }
                else if (allowedOrigin.equals(origin))
                {
                    return true;
                }
            }
        }
        return false;
    }
