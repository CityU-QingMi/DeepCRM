    @SuppressWarnings("")
    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("{} get({},{})",this,contextual,creationalContext);
        }

        Bean<T> bean = (Bean<T>)contextual;

        if (bean.getBeanClass().isAssignableFrom(Session.class))
        {
            return (T)this.session;
        }

        if (beanStore == null)
        {
            beanStore = new SimpleBeanStore();
        }

        List<ScopedInstance<?>> beans = beanStore.getBeans(contextual);

        if ((beans != null) && (!beans.isEmpty()))
        {
            for (ScopedInstance<?> instance : beans)
            {
                if (instance.bean.equals(bean))
                {
                    return (T)instance.instance;
                }
            }
        }

        // no bean found, create it
        T t = bean.create(creationalContext);
        ScopedInstance<T> customInstance = new ScopedInstance<>();
        customInstance.bean = bean;
        customInstance.creationalContext = creationalContext;
        customInstance.instance = t;
        beanStore.addBean(customInstance);
        return t;
    }
