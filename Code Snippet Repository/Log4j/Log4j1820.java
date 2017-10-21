    @Test
    public void testAppendWithSerializedLayout() throws Exception {
        final Appender appender = ctx.getRequiredAppender("KafkaAppenderWithSerializedLayout");
        final LogEvent logEvent = createLogEvent();
        appender.append(logEvent);
        final List<ProducerRecord<byte[], byte[]>> history = kafka.history();
        assertEquals(1, history.size());
        final ProducerRecord<byte[], byte[]> item = history.get(0);
        assertNotNull(item);
        assertEquals(TOPIC_NAME, item.topic());
        assertNull(item.key());
        assertEquals(LOG_MESSAGE, deserializeLogEvent(item.value()).getMessage().getFormattedMessage());
    }
