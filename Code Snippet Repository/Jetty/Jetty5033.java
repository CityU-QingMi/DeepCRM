    public static String truncate(String str, int maxSize)
    {
        if (str == null)
        {
            return null;
        }

        if (str.length() <= maxSize)
        {
            return str;
        }

        return str.substring(0,maxSize);
    }
