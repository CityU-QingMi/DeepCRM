    @Test
    public void testParameterizedPerf() {
        final String testMsg = "Test message {} {}";
        final Timer timer = new Timer("Parameterized", LOOP_CNT);
        timer.start();
        for (int i = 0; i < LOOP_CNT; ++i) {
            final ParameterizedMessage msg = new ParameterizedMessage(testMsg, "Apache", "Log4j");
            array[i] = msg.getFormattedMessage();
        }
        timer.stop();
        paramTime = timer.getElapsedNanoTime();
        System.out.println(timer.toString());
    }
