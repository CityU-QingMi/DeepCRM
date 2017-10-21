    public void setRunAs(Object o)
    {
        if (o == null)
            return;

        if (!ServletHolder.class.isAssignableFrom(o.getClass()))
            return;

        RunAs runAs = (RunAs)_runAsMap.get(o.getClass().getName());
        if (runAs == null)
            return;

        runAs.setRunAs((ServletHolder)o);
    }
