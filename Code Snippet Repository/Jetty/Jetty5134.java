    public static String getLoggingProperty(Properties props, String name, String property)
    {
        // Calculate the level this named logger should operate under.
        // Checking with FQCN first, then each package segment from longest to shortest.
        String nameSegment = name;
    
        while ((nameSegment != null) && (nameSegment.length() > 0))
        {
            String s = props.getProperty(nameSegment+"."+property);
            if (s!=null)
                return s;
    
            // Trim and try again.
            int idx = nameSegment.lastIndexOf('.');
            nameSegment = (idx >= 0)?nameSegment.substring(0,idx):null;
        }
    
        return null;
    }
