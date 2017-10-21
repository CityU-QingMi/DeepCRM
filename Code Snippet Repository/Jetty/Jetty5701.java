    @Test
    public void testInheritedListener() throws Exception
    {
        ContainerLifeCycle c0 = new ContainerLifeCycle()
        {
            public
            @Override
            String toString()
            {
                return "c0";
            }
        };
        ContainerLifeCycle c00 = new ContainerLifeCycle()
        {
            public
            @Override
            String toString()
            {
                return "c00";
            }
        };
        ContainerLifeCycle c01 = new ContainerLifeCycle()
        {
            public
            @Override
            String toString()
            {
                return "c01";
            }
        };
        Container.InheritedListener inherited = new InheritedListenerLifeCycle();

        c0.addBean(c00);
        c0.start();
        c0.addBean(inherited);
        c0.manage(inherited);
        c0.addBean(c01);
        c01.start();
        c0.manage(c01);

        Assert.assertTrue(c0.isManaged(inherited));
        Assert.assertFalse(c00.isManaged(inherited));
        Assert.assertFalse(c01.isManaged(inherited));
    }
