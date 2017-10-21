    public void setRunAs (ServletHolder holder)
    {
        if (holder == null)
            return;
        String className = holder.getClassName();

        if (className.equals(_className))
        {
            //Only set the RunAs if it has not already been set, presumably by web/web-fragment.xml
            if (holder.getRegistration().getRunAsRole() == null)
                holder.getRegistration().setRunAsRole(_roleName);
        }
    }
