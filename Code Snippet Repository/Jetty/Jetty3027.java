    public void handle(Request request, Runnable runnable)
    {
        ClassLoader old_classloader = null;
        Thread current_thread = null;
        Context old_context = __context.get();

        // Are we already in the scope?
        if (old_context==_scontext)
        {
            runnable.run();
            return;
        }

        // Nope, so enter the scope and then exit
        try
        {
            __context.set(_scontext);

            // Set the classloader
            if (_classLoader != null)
            {
                current_thread = Thread.currentThread();
                old_classloader = current_thread.getContextClassLoader();
                current_thread.setContextClassLoader(_classLoader);
            }

            enterScope(request,runnable);
            runnable.run();
        }
        finally
        {
            exitScope(request);

            __context.set(old_context);
            if (old_classloader != null)
            {
                current_thread.setContextClassLoader(old_classloader);
            }
        }
    }
