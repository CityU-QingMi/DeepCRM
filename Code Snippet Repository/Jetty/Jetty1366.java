    public static void requireValidRFC6265CookieValue(String value)
    {
        if (value == null)
        {
            return;
        }
        
        int valueLen = value.length();
        if (valueLen == 0)
        {
            return;
        }
        
        int i = 0;
        if (value.charAt(0) == '"')
        {
            // Has starting DQUOTE
            if (valueLen <= 1 || (value.charAt(valueLen - 1) != '"'))
            {
                throw new IllegalArgumentException("RFC6265 Cookie values must have balanced DQUOTES (if used)");
            }
            
            // adjust search range to exclude DQUOTES
            i++;
            valueLen--;
        }
        for (; i < valueLen; i++)
        {
            char c = value.charAt(i);
            
            // 0x00 - 0x1F are low order control characters
            // 0x7F is the DEL control character
            if ((c <= 0x1F) || (c == 0x7F))
                throw new IllegalArgumentException("RFC6265 Cookie values may not contain control characters");
            if ((c == ' ' /* 0x20 */) ||
                    (c == '"' /* 0x2C */) ||
                    (c == ';' /* 0x3B */) ||
                    (c == '\\' /* 0x5C */))
            {
                throw new IllegalArgumentException("RFC6265 Cookie values may not contain character: [" + c + "]");
            }
            if (c >= 0x80)
                throw new IllegalArgumentException("RFC6265 Cookie values characters restricted to US-ASCII: 0x" + Integer.toHexString(c));
        }
    }
