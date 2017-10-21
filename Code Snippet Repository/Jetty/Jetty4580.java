    public static String join(Object[] arr, int start, int end, String delim)
    {
        if (arr == null)
        {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++)
        {
            if (i > start)
            {
                str.append(delim);
            }
            str.append(arr[i]);
        }
        return str.toString();
    }
