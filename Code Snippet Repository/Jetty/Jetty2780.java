    protected String findContextName(ServletContext context)
    {
        if (context==null)
            return null;
        String n = (String)context.getAttribute(_attr);
        if (n==null)
        {
            n=String.format("%s@%x",context.getContextPath(),context.hashCode());
            context.setAttribute(_attr,n);
        }
        return n;
    }
