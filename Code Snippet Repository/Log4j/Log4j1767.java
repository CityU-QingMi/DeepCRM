    @Test
    public void testBuffering01() throws Exception {
        setUp("name", 0);

        final LogEvent event1 = mock(LogEvent.class);
        final LogEvent event2 = mock(LogEvent.class);
        final LogEvent event3 = mock(LogEvent.class);

        manager.startup();
        then(manager).should().startupInternal();
        reset(manager);

        manager.write(event1);
        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event1));
        then(manager).should().commitAndClose();
        reset(manager);

        manager.write(event2);
        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event2));
        then(manager).should().commitAndClose();
        reset(manager);

        manager.write(event3);
        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event3));
        then(manager).should().commitAndClose();
        then(manager).shouldHaveNoMoreInteractions();
    }
