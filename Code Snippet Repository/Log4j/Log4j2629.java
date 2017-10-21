    @Test
    public void testInteractionWithReusableParameterizedMessage() {
        final MutableLogEvent evt = new MutableLogEvent();
        final ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        msg.set("Hello {} {} {}", 1, 2, 3);
        evt.setMessage(msg);
        evt.clear();

        msg.set("Hello {}", new Object[]{1});
        evt.setMessage(msg);
        evt.clear();

        msg.set("Hello {}", 1);
        evt.setMessage(msg);
        evt.clear();

        // Uncomment out this log event and the params gets reset correctly (No exception occurs)
        //        msg.set("Hello {}", 1);
        //        evt.setMessage(msg);
        //        evt.clear();

        // Exception at this log event - as the params is set to 1!
        msg.set("Hello {} {} {}", 1, 2, 3);
        evt.setMessage(msg);
        evt.clear();
    }
