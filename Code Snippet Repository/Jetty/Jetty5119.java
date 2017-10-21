    @Override
    public void destroy()
    {
        _destroyed = true;
        List<Bean> reverse = new ArrayList<>(_beans);
        Collections.reverse(reverse);
        for (Bean b : reverse)
        {
            if (b._bean instanceof Destroyable && (b._managed==Managed.MANAGED || b._managed==Managed.POJO))
            {
                Destroyable d = (Destroyable)b._bean;
                d.destroy();
            }
        }
        _beans.clear();
    }
