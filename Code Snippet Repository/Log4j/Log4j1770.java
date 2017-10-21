    @Test
    public void testBuffering04() throws Exception {
        setUp("name", 10);

        final LogEvent event1 = mock(LogEvent.class);
        final LogEvent event2 = mock(LogEvent.class);
        final LogEvent event3 = mock(LogEvent.class);

        manager.startup();
        then(manager).should().startupInternal();

        manager.write(event1);
        manager.write(event2);
        manager.write(event3);
        manager.shutdown();

        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event1));
        then(manager).should().writeInternal(same(event2));
        then(manager).should().writeInternal(same(event3));
        then(manager).should().commitAndClose();
        then(manager).should().shutdownInternal();
        then(manager).shouldHaveNoMoreInteractions();
    }
