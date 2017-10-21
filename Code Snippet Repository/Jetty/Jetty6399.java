    public static String dequote(String str)
    {
        char start = str.charAt(0);
        if ((start == '\'') || (start == '\"'))
        {
            // possibly quoted
            char end = str.charAt(str.length() - 1);
            if (start == end)
            {
                // dequote
                return str.substring(1,str.length() - 1);
            }
        }
        return str;
    }
