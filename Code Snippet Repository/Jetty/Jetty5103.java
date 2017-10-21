    private void unmanage(Bean bean)
    {
        if (bean._managed!=Managed.UNMANAGED)
        {
            if (bean._managed==Managed.MANAGED && bean._bean instanceof Container)
            {
                for (Container.Listener l:_listeners)
                {
                    if (l instanceof InheritedListener)
                        ((Container)bean._bean).removeBean(l);
                }
            }
            bean._managed=Managed.UNMANAGED;
        }
    }
