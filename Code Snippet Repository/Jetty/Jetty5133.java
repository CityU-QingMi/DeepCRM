    public static int lookupLoggingLevel(Properties props, final String name)
    {
        if ((props == null) || (props.isEmpty()) || name==null )
            return LEVEL_DEFAULT;
        
        // Calculate the level this named logger should operate under.
        // Checking with FQCN first, then each package segment from longest to shortest.
        String nameSegment = name;
    
        while ((nameSegment != null) && (nameSegment.length() > 0))
        {
            String levelStr = props.getProperty(nameSegment + ".LEVEL");
            // System.err.printf("[StdErrLog.CONFIG] Checking for property [%s.LEVEL] = %s%n",nameSegment,levelStr);
            int level = getLevelId(nameSegment + ".LEVEL",levelStr);
            if (level != (-1))
            {
                return level;
            }
    
            // Trim and try again.
            int idx = nameSegment.lastIndexOf('.');
            if (idx >= 0)
            {
                nameSegment = nameSegment.substring(0,idx);
            }
            else
            {
                nameSegment = null;
            }
        }
    
        // Default Logging Level
        return LEVEL_DEFAULT;
    }
