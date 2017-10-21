    @Override
    public void removeEventListener(Container.Listener listener)
    {
        if (_listeners.remove(listener))
        {
            // remove existing beans
            for (Bean b:_beans)
            {
                listener.beanRemoved(this,b._bean);

                if (listener instanceof InheritedListener && b.isManaged() && b._bean instanceof Container)
                    ((Container)b._bean).removeBean(listener);
            }
        }
    }
