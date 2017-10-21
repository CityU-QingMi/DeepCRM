    @Override
    public void addEventListener(Container.Listener listener)
    {
        if (_listeners.contains(listener))
            return;
        
        _listeners.add(listener);

        // tell it about existing beans
        for (Bean b:_beans)
        {
            listener.beanAdded(this,b._bean);

            // handle inheritance
            if (listener instanceof InheritedListener && b.isManaged() && b._bean instanceof Container)
            {
                if (b._bean instanceof ContainerLifeCycle)
                     ((ContainerLifeCycle)b._bean).addBean(listener, false);
                 else
                     ((Container)b._bean).addBean(listener);
            }
        }
    }
