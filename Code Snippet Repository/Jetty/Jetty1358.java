    public static String unquote(String s)
    {
        // handle trivial cases
        int l=s.length();
        if (s==null || l==0)
            return s;
        
        // Look for any quotes
        int i=0;
        for (;i<l;i++)
        {
            char c=s.charAt(i);
            if (c=='"')
                break;
        }
        if (i==l)
            return s;

        boolean quoted=true;
        boolean sloshed=false;
        StringBuffer buffer = new StringBuffer();
        buffer.append(s,0,i);
        i++;
        for (;i<l;i++)
        {
            char c=s.charAt(i);
            if (quoted)
            {
                if (sloshed)
                {
                    buffer.append(c);
                    sloshed=false;
                }
                else if (c=='"')
                    quoted=false;
                else if (c=='\\')
                    sloshed=true;
                else
                    buffer.append(c);
            }
            else if (c=='"')
                quoted=true;
            else
                buffer.append(c);
        }
        return buffer.toString();
    }
