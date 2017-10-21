    private void verify(final String expected) {
        final ListAppender listApp = context.getListAppender("List");
        final List<String> events = listApp.getMessages();
        try
        {
            assertEquals("Incorrect number of messages.", 1, events.size());
            assertEquals("Incorrect message.", "o.a.l.l.t.ExitTagTest " + expected, events.get(0));
        }
        finally
        {
            listApp.clear();
        }
    }
