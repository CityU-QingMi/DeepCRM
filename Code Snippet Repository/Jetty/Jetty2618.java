    protected void initClassLoader()
    {
        URL[] paths = _classpath.asArray();

        if (_classLoader==null && paths !=null && paths.length > 0)
        {
            ClassLoader context=Thread.currentThread().getContextClassLoader();

            if (context==null)
                _classLoader=new URLClassLoader(paths);
            else
                _classLoader=new URLClassLoader(paths, context);

            Thread.currentThread().setContextClassLoader(_classLoader);
        }
    }
