    public static <T> T runWithServerClassAccess(PrivilegedExceptionAction<T> action) throws Exception
    {
        Boolean lsc=__loadServerClasses.get();
        try
        {
            __loadServerClasses.set(true);
            return action.run();
        }
        finally
        {
            if (lsc==null)
                __loadServerClasses.remove();
            else
                __loadServerClasses.set(lsc);
        }
    }
