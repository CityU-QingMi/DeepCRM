    @Test
    public void testStartStopDestroy() throws Exception
    {
        ContainerLifeCycle a0 = new ContainerLifeCycle();
        TestContainerLifeCycle a1 = new TestContainerLifeCycle();

        a0.start();
        Assert.assertEquals(0, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.addBean(a1);
        Assert.assertEquals(0, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());
        Assert.assertFalse(a0.isManaged(a1));

        a0.start();
        Assert.assertEquals(0, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a1.start();
        a0.manage(a1);
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(0, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.removeBean(a1);
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a0.stop();
        a0.destroy();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a1.stop();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(0, a1.destroyed.get());

        a1.destroy();
        Assert.assertEquals(1, a1.started.get());
        Assert.assertEquals(1, a1.stopped.get());
        Assert.assertEquals(1, a1.destroyed.get());
    }
