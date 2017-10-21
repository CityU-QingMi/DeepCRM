    public static String quoteIfNeeded(String s, String delim)
    {
        if (s==null)
            return null;
        if (s.length()==0)
            return "\"\"";


        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if (c=='\\' || c=='"' || c=='\'' || Character.isWhitespace(c) || delim.indexOf(c)>=0)
            {
                StringBuffer b=new StringBuffer(s.length()+8);
                quote(b,s);
                return b.toString();
            }
        }

        return s;
    }
