    public static String toUtf8String(byte[] buf)
    {
        String raw = StringUtil.toUTF8String(buf,0,buf.length);
        StringBuilder ret = new StringBuilder();
        int len = raw.length();
        for (int i = 0; i < len; i++)
        {
            int codepoint = raw.codePointAt(i);
            if (Character.isUnicodeIdentifierPart(codepoint))
            {
                ret.append(String.format("\\u%04X",codepoint));
            }
            else
            {
                ret.append(Character.toChars(codepoint));
            }
        }
        return ret.toString();
    }
