    @SuppressWarnings({ "" })
    @Override
    public <T> T get(Contextual<T> contextual)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("{} get({})",this,contextual);
        }

        Bean<T> bean = (Bean<T>)contextual;

        if (bean.getBeanClass().isAssignableFrom(Session.class))
        {
            return (T)this.session;
        }
        
        if (beanStore == null)
        {
            return null;
        }

        List<ScopedInstance<?>> beans = beanStore.getBeans(contextual);

        if ((beans != null) && (!beans.isEmpty()))
        {
            return (T)beans.get(0).instance;
        }

        return null;
    }
