    @Test
    public void testBuffering02() throws Exception {
        setUp("name", 4);

        final LogEvent event1 = mock(LogEvent.class);
        final LogEvent event2 = mock(LogEvent.class);
        final LogEvent event3 = mock(LogEvent.class);
        final LogEvent event4 = mock(LogEvent.class);

        manager.startup();
        then(manager).should().startupInternal();

        manager.write(event1);
        manager.write(event2);
        manager.write(event3);
        manager.write(event4);

        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event1));
        then(manager).should().writeInternal(same(event2));
        then(manager).should().writeInternal(same(event3));
        then(manager).should().writeInternal(same(event4));
        then(manager).should().commitAndClose();
        then(manager).shouldHaveNoMoreInteractions();
    }
