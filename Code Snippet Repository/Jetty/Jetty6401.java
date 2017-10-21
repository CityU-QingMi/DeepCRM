    public static void quoteIfNeeded(StringBuilder buf, String str, String delim)
    {
        if (str == null)
        {
            return;
        }
        // check for delimiters in input string
        int len = str.length();
        if (len == 0)
        {
            return;
        }
        int ch;
        for (int i = 0; i < len; i++)
        {
            ch = str.codePointAt(i);
            if (delim.indexOf(ch) >= 0)
            {
                // found a delimiter codepoint. we need to quote it.
                quote(buf,str);
                return;
            }
        }

        // no special delimiters used, no quote needed.
        buf.append(str);
    }
