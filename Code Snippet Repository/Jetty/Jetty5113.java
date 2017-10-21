    @Override
    protected void doStop() throws Exception
    {
        _doStarted = false;
        super.doStop();
        List<Bean> reverse = new ArrayList<>(_beans);
        Collections.reverse(reverse);
        for (Bean b : reverse)
        {
            if (b._managed==Managed.MANAGED && b._bean instanceof LifeCycle)
            {
                LifeCycle l = (LifeCycle)b._bean;
                stop(l);
            }
        }
    }
