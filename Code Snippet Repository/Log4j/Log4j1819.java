    @Test
    public void testAppendWithLayout() throws Exception {
        final Appender appender = ctx.getRequiredAppender("KafkaAppenderWithLayout");
        appender.append(createLogEvent());
        final List<ProducerRecord<byte[], byte[]>> history = kafka.history();
        assertEquals(1, history.size());
        final ProducerRecord<byte[], byte[]> item = history.get(0);
        assertNotNull(item);
        assertEquals(TOPIC_NAME, item.topic());
        assertNull(item.key());
        assertEquals("[" + LOG_MESSAGE + "]", new String(item.value(), StandardCharsets.UTF_8));
    }
