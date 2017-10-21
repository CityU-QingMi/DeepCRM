    @Test
    public void testAppend() {
        setUp("name");
        given(manager.commitAndClose()).willReturn(true);

        final LogEvent event1 = mock(LogEvent.class);
        final LogEvent event2 = mock(LogEvent.class);

        appender.append(event1);
        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event1));
        then(manager).should().commitAndClose();

        reset(manager);

        appender.append(event2);
        then(manager).should().connectAndStart();
        then(manager).should().writeInternal(same(event2));
        then(manager).should().commitAndClose();
    }
