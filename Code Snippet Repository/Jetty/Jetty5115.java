    public void updateBean(Object oldBean, final Object newBean)
    {
        if (newBean!=oldBean)
        {
            if (oldBean!=null)
                removeBean(oldBean);
            if (newBean!=null)
                addBean(newBean);
        }
    }
