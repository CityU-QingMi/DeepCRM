    @Test
    public void testJmsTopicAppenderCompatibility() throws Exception {
        final JmsAppender appender = (JmsAppender) ctx.getRequiredAppender("JmsTopicAppender");
        final LogEvent expected = createLogEvent();
        appender.append(expected);
        then(session).should().createObjectMessage(eq(expected));
        then(objectMessage).should().setJMSTimestamp(anyLong());
        then(messageProducer).should().send(objectMessage);
        appender.stop();
        then(session).should().close();
        then(connection).should().close();
    }
