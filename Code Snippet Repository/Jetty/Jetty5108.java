    @Override
    public void setStopTimeout(long stopTimeout)
    {
        super.setStopTimeout(stopTimeout);
        for (Bean bean : _beans)
        {
            if (bean.isManaged() && bean._bean instanceof AbstractLifeCycle)
                ((AbstractLifeCycle)bean._bean).setStopTimeout(stopTimeout);
        }
    }
