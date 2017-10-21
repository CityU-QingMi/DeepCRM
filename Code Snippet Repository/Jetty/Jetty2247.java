    public void callStartup(WebAppContext context)
    throws Exception
    {
        if (_target != null)
        {
            Set<Class<?>> classes = new HashSet<Class<?>>();

            ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(context.getClassLoader());

            try
            {
                for (String s : _applicableTypeNames)
                    classes.add(Loader.loadClass(s));

                context.getServletContext().setExtendedListenerTypes(true);
                if (LOG.isDebugEnabled())
                {
                    long start = System.nanoTime();
                    _target.onStartup(classes, context.getServletContext());
                    LOG.debug("ContainerInitializer {} called in {}ms", _target.getClass().getName(), TimeUnit.MILLISECONDS.convert(System.nanoTime()-start, TimeUnit.NANOSECONDS));
                }
                else
                    _target.onStartup(classes, context.getServletContext());
            }
            finally
            { 
                context.getServletContext().setExtendedListenerTypes(false);
                Thread.currentThread().setContextClassLoader(oldLoader);
            }
        }
    }
