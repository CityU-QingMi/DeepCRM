    public static StringBuilder encodeString(StringBuilder buf,
                                             String path,
                                             String encode)
    {
        if (buf==null)
        {
            for (int i=0;i<path.length();i++)
            {
                char c=path.charAt(i);
                if (c=='%' || encode.indexOf(c)>=0)
                {    
                    buf=new StringBuilder(path.length()<<1);
                    break;
                }
            }
            if (buf==null)
                return null;
        }

        for (int i=0;i<path.length();i++)
        {
            char c=path.charAt(i);
            if (c=='%' || encode.indexOf(c)>=0)
            {
                buf.append('%');
                StringUtil.append(buf,(byte)(0xff&c),16);
            }
            else
                buf.append(c);
        }

        return buf;
    }
