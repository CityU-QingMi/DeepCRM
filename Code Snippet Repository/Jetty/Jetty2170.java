    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        if (_jars != null)
        {
            for (URL u:_jars)
                builder.append(" "+u.toString());
            return builder.toString();
        }
        else
            return super.toString();
    }
