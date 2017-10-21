    public static String quote(String s)
    {
        if (s==null)
            return null;
        if (s.length()==0)
            return "\"\"";

        StringBuffer b=new StringBuffer(s.length()+8);
        quote(b,s);
        return b.toString();

    }
