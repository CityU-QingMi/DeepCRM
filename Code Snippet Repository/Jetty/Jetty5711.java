    @Test
    public void testRemoveLifecycleListener ()
    throws Exception
    {
        TestLifeCycle lifecycle = new TestLifeCycle();
        TestListener listener = new TestListener();
        lifecycle.addLifeCycleListener(listener);

        lifecycle.start();
        assertTrue("The starting event didn't occur",listener.starting);
        lifecycle.removeLifeCycleListener(listener);
        lifecycle.stop();
        assertFalse("The stopping event occurred", listener.stopping);
    }
