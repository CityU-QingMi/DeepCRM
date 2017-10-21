    private static Path asPath(final String pattern)
    {
        String test = pattern;
        if (test.startsWith("glob:"))
        {
            test = test.substring("glob:".length());
        }
        else if (test.startsWith("regex:"))
        {
            test = test.substring("regex:".length());
        }
        return new File(test).toPath();
    }
