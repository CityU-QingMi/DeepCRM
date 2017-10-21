    @Test
    public void testStartupShutdown02() throws Exception {
        setUp("anotherName02", 0);

        assertEquals("The name is not correct.", "anotherName02", manager.getName());
        assertFalse("The manager should not have started.", manager.isRunning());

        manager.startup();
        then(manager).should().startupInternal();
        assertTrue("The manager should be running now.", manager.isRunning());

        manager.releaseSub(-1, null);
        then(manager).should().shutdownInternal();
        assertFalse("The manager should not be running anymore.", manager.isRunning());
    }
