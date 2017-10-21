    @Test
    public void testTraceEntryMessage() {
        currentLevel = Level.TRACE;
        final FlowMessageFactory fact = new DefaultFlowMessageFactory();

        final ParameterizedMessage paramMsg = new ParameterizedMessage("Tracy {}", "Logan");
        currentEvent = new LogEvent(ENTRY_MARKER.getName(), fact.newEntryMessage(paramMsg), null);

        final ReusableParameterizedMessage msg = ReusableParameterizedMessageTest.set(
                new ReusableParameterizedMessage(), "Tracy {}", "Logan");
        final EntryMessage entry = traceEntry(msg);

        ReusableParameterizedMessageTest.set(msg, "Some other message {}", 123);
        currentEvent = new LogEvent(null, msg, null);
        trace("Some other message {}", 123);

        // ensure original entry message not overwritten
        assertEquals("Tracy Logan", entry.getMessage().getFormattedMessage());

        currentEvent = new LogEvent(EXIT_MARKER.getName(), fact.newExitMessage(entry), null);
        traceExit(entry);

        // ensure original entry message not overwritten
        assertEquals("Tracy Logan", entry.getMessage().getFormattedMessage());
    }
