    public void destroy (Object o)
    {
        LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)_context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        if (callbacks != null)
        {
            try
            {
                callbacks.callPreDestroyCallback(o);
            }
            catch (Exception e)
            {
                LOG.warn("Destroying instance of "+o.getClass(), e);
            }
        }
    }
