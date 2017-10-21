    @Test
    public void testStop() throws Exception
    {
        TestLifeCycle lifecycle = new TestLifeCycle();
        TestListener listener = new TestListener();
        lifecycle.addLifeCycleListener(listener);


        // need to set the state to something other than stopped or stopping or
        // else
        // stop() will return without doing anything

        lifecycle.start();
        lifecycle.setCause(cause);

        try (StacklessLogging stackless = new StacklessLogging(AbstractLifeCycle.class))
        {
            lifecycle.stop();
            assertTrue(false);
        }
        catch(Exception e)
        {
            assertEquals(cause,e);
            assertEquals(cause,listener.getCause());
        }

        lifecycle.setCause(null);

        lifecycle.stop();

        // check that the stopping event has been thrown
        assertTrue("The stopping event didn't occur",listener.stopping);

        // check that the stopped event has been thrown
        assertTrue("The stopped event didn't occur",listener.stopped);

        // check that the stopping event occurs before the stopped event
        assertTrue("The stopping event must occur before the stopped event",listener.stoppingTime <= listener.stoppedTime);
        // System.out.println("STOPING TIME : " + listener.stoppingTime + " : " + listener.stoppedTime);

        // check that the lifecycle's state is stopped
        assertTrue("The lifecycle state is not stooped",lifecycle.isStopped());
    }
