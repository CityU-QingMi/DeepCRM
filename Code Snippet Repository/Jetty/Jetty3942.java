    @Override
    public int compareTo(ServletHolder sh)
    {
        if (sh==this)
            return 0;

        if (sh._initOrder<_initOrder)
            return 1;

        if (sh._initOrder>_initOrder)
            return -1;

        // consider _className, need to position properly when one is configured but not the other
        int c;
        if (_className==null && sh._className==null)
            c=0;
        else if (_className==null)
            c=-1;
        else if (sh._className==null)
            c=1;
        else
            c=_className.compareTo(sh._className);

        // if _initOrder and _className are the same, consider the _name
        if (c==0)
            c=_name.compareTo(sh._name);

        return c;
    }
