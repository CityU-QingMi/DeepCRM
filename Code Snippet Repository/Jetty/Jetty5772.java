    public Resource newResource(File file) throws Exception
    {
        try
        {
            return _class.getConstructor(File.class).newInstance(file);
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
