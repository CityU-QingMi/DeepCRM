    private static List<Object> lookupNamingEntries (List<Object> list, Context context, Class<?> clazz)
    throws NamingException
    {
        try
        {
            NamingEnumeration<Binding> nenum = context.listBindings("");
            while (nenum.hasMoreElements())
            {
                Binding binding = nenum.next();
                if (binding.getObject() instanceof Context)
                    lookupNamingEntries (list, (Context)binding.getObject(), clazz);
                else if (clazz.isInstance(binding.getObject()))
                  list.add(binding.getObject());
            }
        }
        catch (NameNotFoundException e)
        {
            __log.debug("No entries of type "+clazz.getName()+" in context="+context);
        }

        return list;
    }
