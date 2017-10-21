    @Test
    public void testAppendToQueue() throws Exception {
        final JmsAppender appender = (JmsAppender) ctx.getRequiredAppender("JmsAppender");
        final LogEvent event = createLogEvent();
        appender.append(event);
        then(session).should().createTextMessage(eq(LOG_MESSAGE));
        then(textMessage).should().setJMSTimestamp(anyLong());
        then(messageProducer).should().send(textMessage);
        appender.stop();
        then(session).should().close();
        then(connection).should().close();
    }
