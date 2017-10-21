    private static boolean isGlob(char c, boolean syntaxed)
    {
        for (char g : GLOB_CHARS)
        {
            if (c == g)
            {
                return true;
            }
        }
        if (syntaxed)
        {
            for (char g : SYNTAXED_GLOB_CHARS)
            {
                if (c == g)
                {
                    return true;
                }
            }
        }
        return false;
    }
