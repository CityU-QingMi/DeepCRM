    public void addServlet(ServletHolder holder)
    {
        if (holder == null)
            return;
        
        synchronized (this)
        {
            if (!containsServletHolder(holder))
                setServlets(ArrayUtil.addToArray(getServlets(), holder, ServletHolder.class));
        }
    }
