    private static boolean isQuoteNeededForCookie(String s)
    {
        if (s==null || s.length()==0)
            return true;

        if (QuotedStringTokenizer.isQuoted(s))
            return false;

        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if (__COOKIE_DELIM.indexOf(c)>=0)
                return true;

            if (c<0x20 || c>=0x7f)
                throw new IllegalArgumentException("Illegal character in cookie value");
        }

        return false;
    }
