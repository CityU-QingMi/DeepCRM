    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName());
        str.append('[');
        boolean delim = false;
        for (ConfigSource source : sources)
        {
            if (delim)
            {
                str.append(',');
            }
            str.append(source.getId());
            delim = true;
        }
        str.append(']');
        return str.toString();
    }
