    private boolean remove(Bean bean)
    {
        if (_beans.remove(bean))
        {
            boolean wasManaged = bean.isManaged();
            
            unmanage(bean);

            for (Container.Listener l:_listeners)
                l.beanRemoved(this,bean._bean);

            if (bean._bean instanceof Container.Listener)
                removeEventListener((Container.Listener)bean._bean);

            // stop managed beans
            if (wasManaged && bean._bean instanceof LifeCycle)
            {
                try
                {
                    stop((LifeCycle)bean._bean);
                }
                catch(RuntimeException | Error e)
                {
                    throw e;
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }
        return false;
    }
