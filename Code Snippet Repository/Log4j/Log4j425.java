    @Test
    public void testStringPerf() {
        final String testMsg = "Test message %1s %2s";
        final Timer timer = new Timer("StringFormat", LOOP_CNT);
        timer.start();
        for (int i = 0; i < LOOP_CNT; ++i) {
            final StringFormattedMessage msg = new StringFormattedMessage(testMsg, "Apache", "Log4j");
            array[i] = msg.getFormattedMessage();
        }
        timer.stop();
        stringTime = timer.getElapsedNanoTime();
        System.out.println(timer.toString());
    }
