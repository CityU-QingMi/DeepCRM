    @Test
    public void testEvents() {
        ThreadContext.put("loginId", "JohnDoe");
        ThreadContext.put("ipAddress", "192.168.0.120");
        ThreadContext.put("locale", Locale.US.getDisplayName());
        final TransferMessage msg = new TransferMessage();
        msg.put("ToAccount", "123456");
        msg.put("FromAccount", "123457");
        msg.put("Amount", "200.00");
        EventLogger.logEvent(msg);
        msg.setCompletionStatus("Transfer Complete");
        EventLogger.logEvent(msg);
        ThreadContext.clearMap();
        // TODO: do something with the results

    }
