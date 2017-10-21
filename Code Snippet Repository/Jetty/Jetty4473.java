    public void addEqualsArg(String name, String value)
    {
        if ((value != null) && (value.length() > 0))
        {
            args.add(quote(name + "=" + value));
        }
        else
        {
            args.add(quote(name));
        }
    }
