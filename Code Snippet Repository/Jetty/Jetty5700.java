    @Test(expected = IllegalStateException.class)
    public void testIllegalToStartAfterDestroy() throws Exception
    {
        ContainerLifeCycle container = new ContainerLifeCycle();
        container.start();
        container.stop();
        container.destroy();

        // Should throw IllegalStateException.
        container.start();
    }
