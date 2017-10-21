    protected boolean isJspServlet ()
    {
        Servlet servlet = getServletInstance();
        Class<?> c = servlet==null?_class:servlet.getClass();

        while (c != null)
        {
            if (isJspServlet(c.getName()))
                return true;
            c = c.getSuperclass();
        }
        return false;
    }
