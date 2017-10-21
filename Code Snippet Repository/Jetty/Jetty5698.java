    @Test
    public void testStartStop() throws Exception
    {
        ContainerLifeCycle a0 = new ContainerLifeCycle();
        TestContainerLifeCycle a1 = new TestContainerLifeCycle();
        a0.addBean(a1);

        a0.start();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.start();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.stop();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.start();
        Assert.assertEquals(2, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.stop();
        Assert.assertEquals(2, a1.started.get());
        Assert.assertEquals(2, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());
    }
