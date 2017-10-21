    public void call(Object obj, Session connection, Object... args)
    {
        if (wantsSession)
        {
            Object fullArgs[] = new Object[args.length + 1];
            fullArgs[0] = connection;
            System.arraycopy(args,0,fullArgs,1,args.length);
            call(obj,fullArgs);
        }
        else
        {
            call(obj,args);
        }
    }
