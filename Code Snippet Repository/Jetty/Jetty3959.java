    public String getObjectNameBasis()
    {
        if (_managed!=null && _managed instanceof Holder)
        {
            Holder holder = (Holder)_managed;
            String name = holder.getName();
            if (name!=null)
                return name;
        }
        return super.getObjectNameBasis();
    }
