    public static Map flattenBindings (Context ctx, String name)
    throws NamingException
    {
        HashMap map = new HashMap();

        //the context representation of name arg
        Context c = (Context)ctx.lookup (name);
        NameParser parser = c.getNameParser("");
        NamingEnumeration enm = ctx.listBindings(name);
        while (enm.hasMore())
        {
            Binding b = (Binding)enm.next();

            if (b.getObject() instanceof Context)
            {
                map.putAll(flattenBindings (c, b.getName()));
            }
            else
            {
                Name compoundName = parser.parse (c.getNameInNamespace());
                compoundName.add(b.getName());
                map.put (compoundName.toString(), b.getObject());
            }

        }

        return map;
    }
