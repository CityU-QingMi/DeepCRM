    @Test
    public void testStartupShutdown01() throws Exception {
        setUp("testName01", 0);

        assertEquals("The name is not correct.", "testName01", manager.getName());
        assertFalse("The manager should not have started.", manager.isRunning());

        manager.startup();
        then(manager).should().startupInternal();
        assertTrue("The manager should be running now.", manager.isRunning());

        manager.shutdown();
        then(manager).should().shutdownInternal();
        assertFalse("The manager should not be running anymore.", manager.isRunning());
    }
