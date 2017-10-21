    @After
    public void after() throws Exception
    {
        InitialContext icontext = new InitialContext();

        NamingEnumeration<Binding> bindings = icontext.listBindings("");
        List<String> names = new ArrayList<String>();
        while (bindings.hasMore())
        {
            Binding bd = (Binding)bindings.next();
            names.add(bd.getName());
        }

        for (String name : names)
        {
            icontext.unbind(name);
        }
    }
