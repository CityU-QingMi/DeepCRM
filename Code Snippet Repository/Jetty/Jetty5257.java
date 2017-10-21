    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (null == o)
            return false;

        if (o instanceof Password)
            return stringEquals(_pw, ((Password)o)._pw);

        if (o instanceof String)
            return stringEquals(_pw, (String)o);

        return false;
    }
