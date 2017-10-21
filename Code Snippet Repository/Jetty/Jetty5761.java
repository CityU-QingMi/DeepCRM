    public Resource newResource(URI uri) throws Exception
    {
        try
        {
            return _class.getConstructor(URI.class).newInstance(uri);
        }
        catch(InvocationTargetException e)
        {
            try
            {
                throw e.getTargetException();
            }
            catch(Exception|Error ex)
            {
                throw ex;
            }
            catch(Throwable th)
            {
                throw new Error(th);
            }
        }
    }
