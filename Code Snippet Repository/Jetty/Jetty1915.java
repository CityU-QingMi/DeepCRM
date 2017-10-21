    public static void unbind (Context ctx)
    throws NamingException
    {
        //unbind everything in the context and all of its subdirectories
        NamingEnumeration ne = ctx.listBindings(ctx.getNameInNamespace());

        while (ne.hasMoreElements())
        {
            Binding b = (Binding)ne.nextElement();
            if (b.getObject() instanceof Context)
            {
                unbind((Context)b.getObject());
            }
            else
                ctx.unbind(b.getName());
        }
    }
