    @Override
    public Class<?> resolveClass (java.io.ObjectStreamClass cl) throws IOException, ClassNotFoundException
    {
        try
        {
            return Class.forName(cl.getName(), false, Thread.currentThread().getContextClassLoader());
        }
        catch (ClassNotFoundException e)
        {
            return super.resolveClass(cl);
        }
    }
