    public void testWildCardEvaluation() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy("Abstract-crud", "edit", null, null);
        assertEquals("com.opensymphony.xwork2.SimpleAction", proxy.getConfig().getClassName());
        
        proxy = actionProxyFactory.createActionProxy("/example", "edit", null, null);
        assertEquals("com.opensymphony.xwork2.ModelDrivenAction", proxy.getConfig().getClassName());
         

        proxy = actionProxyFactory.createActionProxy("/example2", "override", null, null);
        assertEquals("com.opensymphony.xwork2.ModelDrivenAction", proxy.getConfig().getClassName());
        
        proxy = actionProxyFactory.createActionProxy("/example2/subItem", "save", null, null);
        assertEquals("com.opensymphony.xwork2.ModelDrivenAction", proxy.getConfig().getClassName());
        
        proxy = actionProxyFactory.createActionProxy("/example2", "list", null, null);
        assertEquals("com.opensymphony.xwork2.ModelDrivenAction", proxy.getConfig().getClassName());
        
        proxy = actionProxyFactory.createActionProxy("/example3", "list", null, null);
        assertEquals("com.opensymphony.xwork2.SimpleAction", proxy.getConfig().getClassName());
    }
