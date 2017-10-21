    public Context createSubcontext(String name) throws NamingException
    {
        synchronized (__root)
        {
            //if the subcontext comes directly off the root, use the env of the InitialContext
            //as the root itself has no environment. Otherwise, it inherits the env of the parent
            //Context further down the tree.
            //NamingContext ctx = (NamingContext)__root.createSubcontext(name);
            //if (ctx.getParent() == __root)
            //    ctx.setEnv(_env);
            //return ctx;

            return createSubcontext(__root.getNameParser("").parse(name));
        }
    }
