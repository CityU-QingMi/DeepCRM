    public static String[] arrayFromString(String s) 
    {
        if (s==null)
            return new String[]{};

        if (!s.startsWith("[") || !s.endsWith("]"))
            throw new IllegalArgumentException();
        if (s.length()==2)
            return new String[]{};

        return csvSplit(s,1,s.length()-2);
    }
