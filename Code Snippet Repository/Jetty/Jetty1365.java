    public static void requireValidRFC2616Token(String value, String msg)
    {
        Objects.requireNonNull(msg, "msg cannot be null");
        
        if (value == null)
        {
            return;
        }
        
        int valueLen = value.length();
        if (valueLen == 0)
        {
            return;
        }
        
        for (int i = 0; i < valueLen; i++)
        {
            char c = value.charAt(i);
            
            // 0x00 - 0x1F are low order control characters
            // 0x7F is the DEL control character
            if ((c <= 0x1F) || (c == 0x7F))
                throw new IllegalArgumentException(msg + ": RFC2616 tokens may not contain control characters");
            if (c == '(' || c == ')' || c == '<' || c == '>' || c == '@'
                    || c == ',' || c == ';' || c == ':' || c == '\\' || c == '"'
                    || c == '/' || c == '[' || c == ']' || c == '?' || c == '='
                    || c == '{' || c == '}' || c == ' ')
            {
                throw new IllegalArgumentException(msg + ": RFC2616 tokens may not contain separator character: [" + c + "]");
            }
            if (c >= 0x80)
                throw new IllegalArgumentException(msg + ": RFC2616 tokens characters restricted to US-ASCII: 0x" + Integer.toHexString(c));
        }
    }
