    public static PathMatcher getMatcher(final String rawpattern)
    {
        FileSystem fs = FileSystems.getDefault();
        
        String pattern = rawpattern;
        
        // Strip trailing slash (if present)
        int lastchar = pattern.charAt(pattern.length() - 1);
        if (lastchar == '/' || lastchar == '\\')
        {
            pattern = pattern.substring(0,pattern.length() - 1);
        }

        // If using FileSystem.getPathMatcher() with "glob:" or "regex:"
        // use FileSystem default pattern behavior
        if (pattern.startsWith("glob:") || pattern.startsWith("regex:"))
        {
            StartLog.debug("Using Standard " + fs.getClass().getName() + " pattern: " + pattern);
            return fs.getPathMatcher(pattern);
        }

        // If the pattern starts with a root path then its assumed to
        // be a full system path
        if (isAbsolute(pattern))
        {
            String pat = "glob:" + pattern;
            StartLog.debug("Using absolute path pattern: " + pat);
            return fs.getPathMatcher(pat);
        }

        // Doesn't start with filesystem root, then assume the pattern
        // is a relative file path pattern.
        String pat = "glob:**/" + pattern;
        StartLog.debug("Using relative path pattern: " + pat);
        return fs.getPathMatcher(pat);
    }
