    private void manage(Bean bean)
    {
        if (bean._managed!=Managed.MANAGED)
        {
            bean._managed=Managed.MANAGED;

            if (bean._bean instanceof Container)
            {
                for (Container.Listener l:_listeners)
                {
                    if (l instanceof InheritedListener)
                    {
                        if (bean._bean instanceof ContainerLifeCycle)
                            ((ContainerLifeCycle)bean._bean).addBean(l,false);
                        else
                            ((Container)bean._bean).addBean(l);
                    }
                }
            }

            if (bean._bean instanceof AbstractLifeCycle)
            {
                ((AbstractLifeCycle)bean._bean).setStopTimeout(getStopTimeout());
            }
        }
    }
