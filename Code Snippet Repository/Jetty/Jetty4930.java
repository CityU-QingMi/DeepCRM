    public void add(Throwable e)
    {
        if (e==null)
            throw new IllegalArgumentException();

        if(nested == null)
        {
            initCause(e);
            nested = new ArrayList<>();
        }
        else
            addSuppressed(e);
        
        if (e instanceof MultiException)
        {
            MultiException me = (MultiException)e;
            nested.addAll(me.nested);
        }
        else
            nested.add(e);
    }
