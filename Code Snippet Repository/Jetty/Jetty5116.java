    public void updateBean(Object oldBean, final Object newBean, boolean managed)
    {
        if (newBean!=oldBean)
        {
            if (oldBean!=null)
                removeBean(oldBean);
            if (newBean!=null)
                addBean(newBean,managed);
        }
    }
