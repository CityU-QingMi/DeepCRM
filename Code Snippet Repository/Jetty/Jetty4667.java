    @Override
    public Statement apply(final Statement statement, Description description)
    {
        return new Statement()
        {
            @Override
            public void evaluate() throws Throwable
            {
                SecurityManager origSecurityManager = System.getSecurityManager();
                try
                {
                    System.setSecurityManager(new NoExitSecurityManager());
                    statement.evaluate();
                }
                finally
                {
                    System.setSecurityManager(origSecurityManager);
                }
            }
        };
    }
