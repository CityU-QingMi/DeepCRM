    public void call(Object obj, Object... args)
    {
        if ((this.pojo == null) || (this.method == null))
        {
            LOG.warn("Cannot execute call: pojo={}, method={}",pojo,method);
            return; // no call event method determined
        }
        if (obj == null)
        {
            LOG.warn("Cannot call {} on null object",this.method);
            return;
        }
        if (args.length > paramTypes.length)
        {
            Object trimArgs[] = dropFirstArg(args);
            call(obj,trimArgs);
            return;
        }
        if (args.length < paramTypes.length)
        {
            throw new IllegalArgumentException("Call arguments length [" + args.length + "] must always be greater than or equal to captured args length ["
                    + paramTypes.length + "]");
        }

        try
        {
            this.method.invoke(obj,args);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            String err = String.format("Cannot call method %s on %s with args: %s",method,pojo, QuoteUtil.join(args,","));
            throw new WebSocketException(err,e);
        }
    }
