        @Override
        public ClassLoader getClassLoader()
        {
            if (!_enabled)
                throw new UnsupportedOperationException();

            //no security manager just return the classloader
            if (!_usingSecurityManager)
                return _classLoader;
            else
            {
                //check to see if the classloader of the caller is the same as the context
                //classloader, or a parent of it
                try
                {
                    Class<?> reflect = Loader.loadClass("sun.reflect.Reflection");
                    Method getCallerClass = reflect.getMethod("getCallerClass", Integer.TYPE);
                    Class<?> caller = (Class<?>)getCallerClass.invoke(null, 2);

                    boolean ok = false;
                    ClassLoader callerLoader = caller.getClassLoader();
                    while (!ok && callerLoader != null)
                    {
                        if (callerLoader == _classLoader)
                            ok = true;
                        else
                            callerLoader = callerLoader.getParent();
                    }

                    if (ok)
                        return _classLoader;
                }
                catch (Exception e)
                {
                    LOG.warn("Unable to check classloader of caller",e);
                }

                AccessController.checkPermission(new RuntimePermission("getClassLoader"));
                return _classLoader;
            }
        }
