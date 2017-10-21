    protected DriverDelegate getDelegate() throws NoSuchDelegateException {
        synchronized(this) {
            if(null == delegate) {
                try {
                    if(delegateClassName != null) {
                        delegateClass = getClassLoadHelper().loadClass(delegateClassName, DriverDelegate.class);
                    }

                    delegate = delegateClass.newInstance();
                    
                    delegate.initialize(getLog(), tablePrefix, instanceName, instanceId, getClassLoadHelper(), canUseProperties(), getDriverDelegateInitString());
                    
                } catch (InstantiationException e) {
                    throw new NoSuchDelegateException("Couldn't create delegate: "
                            + e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    throw new NoSuchDelegateException("Couldn't create delegate: "
                            + e.getMessage(), e);
                } catch (ClassNotFoundException e) {
                    throw new NoSuchDelegateException("Couldn't load delegate class: "
                            + e.getMessage(), e);
                }
            }
            return delegate;
        }
    }
