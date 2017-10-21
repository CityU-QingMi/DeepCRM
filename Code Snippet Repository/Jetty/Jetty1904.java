    private Collection<Listener> findListeners()
    {
        Collection<Listener> list = new ArrayList<Listener>();
        NamingContext ctx=this;
        while (ctx!=null)
        {
            if (ctx._listeners!=null)
                list.addAll(ctx._listeners);
            ctx=(NamingContext)ctx.getParent();
        }
        return list;
    }
