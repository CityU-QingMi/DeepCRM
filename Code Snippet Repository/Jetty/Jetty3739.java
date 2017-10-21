    @Test
    public void testProtected() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        String[] protectedTargets = {"/foo-inf", "/bar-inf"};
        handler.setProtectedTargets(protectedTargets);

        Assert.assertTrue(handler.isProtectedTarget("/foo-inf/x/y/z"));
        Assert.assertFalse(handler.isProtectedTarget("/foo/x/y/z"));
        Assert.assertTrue(handler.isProtectedTarget("/foo-inf?x=y&z=1"));
        Assert.assertFalse(handler.isProtectedTarget("/foo-inf-bar"));

        protectedTargets = new String[4];
        System.arraycopy(handler.getProtectedTargets(), 0, protectedTargets, 0, 2);
        protectedTargets[2] = "/abc";
        protectedTargets[3] = "/def";
        handler.setProtectedTargets(protectedTargets);

        Assert.assertTrue(handler.isProtectedTarget("/foo-inf/x/y/z"));
        Assert.assertFalse(handler.isProtectedTarget("/foo/x/y/z"));
        Assert.assertTrue(handler.isProtectedTarget("/foo-inf?x=y&z=1"));
        Assert.assertTrue(handler.isProtectedTarget("/abc/124"));
        Assert.assertTrue(handler.isProtectedTarget("//def"));

        Assert.assertTrue(handler.isProtectedTarget("/ABC/7777"));
    }
