    public static boolean isBlank(String str)
    {
        if (str == null)
        {
            return true;
        }

        int len = str.length();
        if (len == 0)
        {
            return true;
        }

        char c;
        for (int i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            if (Character.isWhitespace(c) == false)
            {
                return false;
            }
        }

        return true;
    }
