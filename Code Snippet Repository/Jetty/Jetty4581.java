    public static String join(Collection<?> objs, String delim)
    {
        if (objs == null)
        {
            return "";
        }
        StringBuilder str = new StringBuilder();
        boolean needDelim = false;
        for (Object obj : objs)
        {
            if (needDelim)
            {
                str.append(delim);
            }
            str.append(obj);
            needDelim = true;
        }
        return str.toString();
    }
