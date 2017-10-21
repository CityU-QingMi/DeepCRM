    public static void escape(StringBuilder buf, String str)
    {
        for (char c : str.toCharArray())
        {
            if (c >= 32)
            {
                // non special character
                if ((c == '"') || (c == '\\'))
                {
                    buf.append('\\');
                }
                buf.append(c);
            }
            else
            {
                // special characters, requiring escaping
                char escaped = escapes[c];

                // is this a unicode escape?
                if (escaped == UNICODE_TAG)
                {
                    buf.append("\\u00");
                    if (c < 0x10)
                    {
                        buf.append('0');
                    }
                    buf.append(Integer.toString(c,16)); // hex
                }
                else
                {
                    // normal escape
                    buf.append('\\').append(escaped);
                }
            }
        }
    }
