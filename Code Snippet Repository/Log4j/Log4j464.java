    @Test
    public void testUseConstructorThread() throws InterruptedException { // LOG4J2-763
        final ThreadDumpMessage msg = new ThreadDumpMessage("Test");

        final String[] actual = new String[1];
        final Thread other = new Thread("OtherThread") {
            @Override
            public void run() {
                actual[0] = msg.getFormattedMessage();
            }
        };
        other.start();
        other.join();

        assertTrue("No mention of other thread in msg", !actual[0].contains("OtherThread"));
    }
