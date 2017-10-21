    @Test
    public void testMessage() {
        final ThreadDumpMessage msg = new ThreadDumpMessage("Testing");

        final String message = msg.getFormattedMessage();
        //System.out.print(message);
        assertTrue("No header", message.contains("Testing"));
        assertTrue("No RUNNABLE", message.contains("RUNNABLE"));
        assertTrue("No ThreadDumpMessage", message.contains("ThreadDumpMessage"));
    }
