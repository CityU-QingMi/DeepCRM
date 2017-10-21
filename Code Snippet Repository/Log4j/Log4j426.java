    @Test
    public void testMessageFormatPerf() {
        final String testMsg = "Test message {0} {1}";
        final Timer timer = new Timer("MessageFormat", LOOP_CNT);
        timer.start();
        for (int i = 0; i < LOOP_CNT; ++i) {
            final MessageFormatMessage msg = new MessageFormatMessage(testMsg, "Apache", "Log4j");
            array[i] = msg.getFormattedMessage();
        }
        timer.stop();
        msgFormatTime = timer.getElapsedNanoTime();
        System.out.println(timer.toString());
    }
