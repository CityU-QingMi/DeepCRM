    public static String join(Collection<?> objs, String delim)
    {
        if (objs == null)
        {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        boolean needDelim = false;
        for (Object obj : objs)
        {
            if (needDelim)
            {
                ret.append(delim);
            }
            if (obj instanceof String)
            {
                ret.append('"').append(obj).append('"');
            }
            else
            {
                ret.append(obj);
            }
            needDelim = true;
        }
        return ret.toString();
    }
