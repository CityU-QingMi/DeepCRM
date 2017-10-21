    @Override
    public void contextInitialized(ServletContextEvent event)
    {                 
        // Update existing attributes
        Enumeration<String> e = event.getServletContext().getAttributeNames();
        while (e.hasMoreElements())
        {
            String name = e.nextElement();
            if (_managedAttributes.contains(name))
                updateBean(name,null,event.getServletContext().getAttribute(name));
        }
    }
