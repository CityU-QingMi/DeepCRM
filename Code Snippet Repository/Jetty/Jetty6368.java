    private String join(Collection<?> coll, String delim)
    {
        StringBuilder buf = new StringBuilder();
        boolean needDelim = false;
        for (Object obj : coll)
        {
            if (needDelim)
            {
                buf.append(delim);
            }
            buf.append(Objects.toString(obj));
            needDelim = true;
        }

        return buf.toString();
    }
