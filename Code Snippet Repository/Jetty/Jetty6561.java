    private RuntimeException unwrapRuntimeException(String err, final Throwable t)
    {
        Throwable ret = t;

        while (ret instanceof InvocationTargetException)
        {
            ret = ((InvocationTargetException)ret).getCause();
        }

        if (ret instanceof RuntimeException)
        {
            return (RuntimeException)ret;
        }

        return new RuntimeException(err,ret);
    }
