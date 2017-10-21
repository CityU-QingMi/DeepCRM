    public NamingContext newNamingContext(Object obj, ClassLoader loader, Hashtable env, Name name, Context parentCtx)
    throws Exception
    {
        Reference ref = (Reference)obj;
        StringRefAddr parserAddr = (StringRefAddr)ref.get("parser");
        String parserClassName = (parserAddr==null?null:(String)parserAddr.getContent());
        NameParser parser = (NameParser)(parserClassName==null?null:loader.loadClass(parserClassName).newInstance());

        return new NamingContext (env,
                                  name.get(0),
                                  (NamingContext)parentCtx,
                                  parser);
    }
