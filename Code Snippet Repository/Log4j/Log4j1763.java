    @Test
    public void testReplaceManager() throws Exception {
        setUp("name");

        final LocalAbstractDatabaseManager oldManager = appender.getManager();
        assertSame("The manager should be the same.", manager, oldManager);

        final LocalAbstractDatabaseManager newManager = mock(LocalAbstractDatabaseManager.class);
        appender.replaceManager(newManager);
        then(manager).should().close();
        then(newManager).should().startupInternal();

        appender.stop();
        then(newManager).should().stop(0L, TimeUnit.MILLISECONDS);
    }
