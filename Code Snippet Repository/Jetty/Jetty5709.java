    @Test
    public void testStart() throws Exception
    {
        TestLifeCycle lifecycle = new TestLifeCycle();
        TestListener listener = new TestListener();
        lifecycle.addLifeCycleListener(listener);


        lifecycle.setCause(cause);

        try (StacklessLogging stackless = new StacklessLogging(AbstractLifeCycle.class))
        {
            lifecycle.start();
            assertTrue(false);
        }
        catch(Exception e)
        {
            assertEquals(cause,e);
            assertEquals(cause,listener.getCause());
        }
        lifecycle.setCause(null);

        lifecycle.start();

        // check that the starting event has been thrown
        assertTrue("The staring event didn't occur",listener.starting);

        // check that the started event has been thrown
        assertTrue("The started event didn't occur",listener.started);

        // check that the starting event occurs before the started event
        assertTrue("The starting event must occur before the started event",listener.startingTime <= listener.startedTime);

        // check that the lifecycle's state is started
        assertTrue("The lifecycle state is not started",lifecycle.isStarted());
    }
