    private ServletHolder getServletHolderForClass (Class clazz)
    {
        ServletHolder holder = null;
        ServletHolder[] holders = _context.getServletHandler().getServlets();
        if (holders != null)
        {
            for (ServletHolder h : holders)
            {
                if (h.getClassName() != null && h.getClassName().equals(clazz.getName()))
                {
                    holder = h;
                }
            }
        }
        return holder;
    }
