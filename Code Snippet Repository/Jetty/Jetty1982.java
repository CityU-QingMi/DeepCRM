    public String addPattern (String s, String pattern)
    {
        if (s == null)
            s = "";
        else
            s = s.trim();
        
        if (!s.contains(pattern))
        {
            if (s.length() != 0)
                s = s + "|";
            s = s + pattern;
        }
        
        return s;
    }
