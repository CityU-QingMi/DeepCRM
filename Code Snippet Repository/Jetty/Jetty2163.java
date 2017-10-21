    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException
    {
        try
        {
            return _osgiBundleClassLoader.loadClass(name);
        }
        catch (ClassNotFoundException cne)
        {
            try
            {
                return super.loadClass(name);
            }
            catch (ClassNotFoundException cne2)
            {
                throw cne;
            }
        }
    }
