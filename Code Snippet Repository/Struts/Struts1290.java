    public void testResolveModel() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        ctx.setSession(new HashMap<String, Object>());

        ObjectFactory factory = ActionContext.getContext().getContainer().getInstance(ObjectFactory.class);
        Object obj = inter.resolveModel(factory, ctx, "java.lang.String", "request", "foo");
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertTrue(obj == ctx.get("foo"));

        obj = inter.resolveModel(factory, ctx, "java.lang.String", "session", "foo");
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertTrue(obj == ctx.getSession().get("foo"));

        obj = inter.resolveModel(factory, ctx, "java.lang.String", "session", "foo");
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertTrue(obj == ctx.getSession().get("foo"));
    }
