    private static Object[] dropFirstArg(Object[] args)
    {
        if (args.length == 1)
        {
            return new Object[0];
        }
        Object ret[] = new Object[args.length - 1];
        System.arraycopy(args,1,ret,0,ret.length);
        return ret;
    }
