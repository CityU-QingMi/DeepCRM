    public Object call(Object obj, Object... args)
    {
        if ((this.pojo == null) || (this.method == null))
        {
            LOG.warn("Cannot execute call: pojo={}, method={}",pojo,method);
            return null; // no call event method determined
        }

        if (obj == null)
        {
            String err = String.format("Cannot call %s on null object", this.method);
            LOG.warn(new RuntimeException(err));            
            return null;
        }

        if (args.length < paramTypes.length)
        {
            throw new IllegalArgumentException("Call arguments length [" + args.length + "] must always be greater than or equal to captured args length ["
                    + paramTypes.length + "]");
        }

        try
        {
            return this.method.invoke(obj,args);
        }
        catch (Throwable t)
        {
            String err = formatMethodCallError(args);
            throw unwrapRuntimeException(err,t);
        }
    }
