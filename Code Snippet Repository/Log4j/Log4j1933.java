    @Test
    public void routingTest() throws NamingException {
        // default route when there's no jndi resource
        StructuredDataMessage msg = new StructuredDataMessage("Test", "This is a message from unknown context", "Context");
        EventLogger.logEvent(msg);
        final File defaultLogFile = new File("target/routingbyjndi/routingbyjnditest-unknown.log");
        assertTrue("The default log file was not created", defaultLogFile.exists());

        // now set jndi resource to Application1
        final Context context = new InitialContext();
        context.bind(JNDI_CONTEXT_NAME, "Application1");

        msg = new StructuredDataMessage("Test", "This is a message from Application1", "Context");
        EventLogger.logEvent(msg);
        assertNotNull("No events generated", listAppender1.getEvents());
        assertTrue("Incorrect number of events. Expected 1, got " + listAppender1.getEvents().size(), listAppender1.getEvents().size() == 1);

        // now set jndi resource to Application2
        context.rebind(JNDI_CONTEXT_NAME, "Application2");

        msg = new StructuredDataMessage("Test", "This is a message from Application2", "Context");
        EventLogger.logEvent(msg);
        assertNotNull("No events generated", listAppender2.getEvents());
        assertTrue("Incorrect number of events. Expected 1, got " + listAppender2.getEvents().size(), listAppender2.getEvents().size() == 1);
        assertTrue("Incorrect number of events. Expected 1, got " + listAppender1.getEvents().size(), listAppender1.getEvents().size() == 1);

        msg = new StructuredDataMessage("Test", "This is another message from Application2", "Context");
        EventLogger.logEvent(msg);
        assertNotNull("No events generated", listAppender2.getEvents());
        assertTrue("Incorrect number of events. Expected 2, got " + listAppender2.getEvents().size(), listAppender2.getEvents().size() == 2);
        assertTrue("Incorrect number of events. Expected 1, got " + listAppender1.getEvents().size(), listAppender1.getEvents().size() == 1);

        // now set jndi resource to Application3.
        // The context name, 'Application3', will be used as log file name by the default route.
        context.rebind("java:comp/env/logging/context-name", "Application3");
        msg = new StructuredDataMessage("Test", "This is a message from Application3", "Context");
        EventLogger.logEvent(msg);
        final File application3LogFile = new File("target/routingbyjndi/routingbyjnditest-Application3.log");
        assertTrue("The Application3 log file was not created", application3LogFile.exists());

        // now set jndi resource to Application4
        // The context name, 'Application4', will be used as log file name by the default route.
        context.rebind("java:comp/env/logging/context-name", "Application4");
        msg = new StructuredDataMessage("Test", "This is a message from Application4", "Context");
        EventLogger.logEvent(msg);
        final File application4LogFile = new File("target/routingbyjndi/routingbyjnditest-Application4.log");
        assertTrue("The Application3 log file was not created", application4LogFile.exists());
    }
