    public static <T extends HandlerContainer> T findContainerOf(HandlerContainer root,Class<T>type, Handler handler)
    {
        if (root==null || handler==null)
            return null;

        Handler[] branches=root.getChildHandlersByClass(type);
        if (branches!=null)
        {
            for (Handler h:branches)
            {
                @SuppressWarnings("unchecked")
                T container = (T)h;
                Handler[] candidates = container.getChildHandlersByClass(handler.getClass());
                if (candidates!=null)
                {
                    for (Handler c:candidates)
                        if (c==handler)
                            return container;
                }
            }
        }
        return null;
    }
