    public static String addPaths(String p1, String p2)
    {
        if (p1==null || p1.length()==0)
        {
            if (p1!=null && p2==null)
                return p1;
            return p2;
        }
        if (p2==null || p2.length()==0)
            return p1;
        
        boolean p1EndsWithSlash = p1.endsWith(SLASH);
        boolean p2StartsWithSlash = p2.startsWith(SLASH);
        
        if (p1EndsWithSlash && p2StartsWithSlash)
        {
            if (p2.length()==1)
                return p1;
            if (p1.length()==1)
                return p2;
        }
        
        StringBuilder buf = new StringBuilder(p1.length()+p2.length()+2);
        buf.append(p1);
        
        if (p1.endsWith(SLASH))
        {
            if (p2.startsWith(SLASH))
                buf.setLength(buf.length()-1);
        }
        else
        {
            if (!p2.startsWith(SLASH))
                buf.append(SLASH);
        }
        buf.append(p2);

        return buf.toString();
    }
