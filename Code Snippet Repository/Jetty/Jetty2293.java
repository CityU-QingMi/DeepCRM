    public Object decorate (Object o)
    {

        RunAsCollection runAses = (RunAsCollection)_context.getAttribute(RunAsCollection.RUNAS_COLLECTION);
        if (runAses != null)
            runAses.setRunAs(o);

        InjectionCollection injections = (InjectionCollection)_context.getAttribute(InjectionCollection.INJECTION_COLLECTION);
        if (injections != null)
            injections.inject(o);

        LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)_context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        if (callbacks != null)
        {
            try
            {
                callbacks.callPostConstructCallback(o);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        return o;
    }
