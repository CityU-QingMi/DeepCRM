    public static boolean isNotBlank(String value)
    {
        if (value == null)
        {
            return false;
        }
        int len = value.length();
        for (int i = 0; i < len; i++)
        {
            int c = value.codePointAt(i);
            if (!Character.isWhitespace(c))
            {
                return true;
            }
        }
        return false;
    }
