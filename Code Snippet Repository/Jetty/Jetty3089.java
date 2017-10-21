    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        Enumeration<String> e = _context.getServletContext().getAttributeNames();
        while (e.hasMoreElements())
        {
            String name = e.nextElement();
            if (_managedAttributes.contains(name))
                updateBean(name,event.getServletContext().getAttribute(name),null);
        }
    }
