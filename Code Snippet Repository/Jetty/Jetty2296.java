    public void start(WebAppContext context, Descriptor descriptor)
    {

        InjectionCollection injections = (InjectionCollection)context.getAttribute(InjectionCollection.INJECTION_COLLECTION);
        if (injections == null)
        {
            injections = new InjectionCollection();
            context.setAttribute(InjectionCollection.INJECTION_COLLECTION, injections);
        }

        LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        if (callbacks == null)
        {
            callbacks = new LifeCycleCallbackCollection();
            context.setAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION, callbacks);
        }

        RunAsCollection runAsCollection = (RunAsCollection)context.getAttribute(RunAsCollection.RUNAS_COLLECTION);
        if (runAsCollection == null)
        {
            runAsCollection = new RunAsCollection();
            context.setAttribute(RunAsCollection.RUNAS_COLLECTION, runAsCollection);
        }
    }
