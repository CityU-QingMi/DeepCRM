    @Test
    public void testFormattedParameterizedPerf() {
        final String testMsg = "Test message {} {}";
        final Timer timer = new Timer("FormattedParameterized", LOOP_CNT);
        timer.start();
        for (int i = 0; i < LOOP_CNT; ++i) {
            final FormattedMessage msg = new FormattedMessage(testMsg, "Apache", "Log4j");
            array[i] = msg.getFormattedMessage();
        }
        timer.stop();
        formattedTime = timer.getElapsedNanoTime();
        System.out.println(timer.toString());
    }
