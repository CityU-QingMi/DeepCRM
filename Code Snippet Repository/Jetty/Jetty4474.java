    public String toString(String delim)
    {
        StringBuilder buf = new StringBuilder();

        for (String arg : args)
        {
            if (buf.length()>0)
            {
                buf.append(delim);
            }
            buf.append(quote(arg));
        }

        return buf.toString();
    }
