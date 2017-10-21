    protected void sendInfoStructuredMessage() {
        ThreadContext.put("loginId", "JohnDoe");
        ThreadContext.put("ipAddress", "192.168.0.120");
        ThreadContext.put("locale", Locale.US.getDisplayName());
        final StructuredDataMessage msg = new StructuredDataMessage("Transfer@18060", "Transfer Complete", "Audit");
        msg.put("ToAccount", "123456");
        msg.put("FromAccount", "123457");
        msg.put("Amount", "200.00");
        // the msg.toString() doesn't contain the parameters of the ThreadContext, so we must use the line1 string
        final String str = msg.asString(null, null);
        sentMessages.add(str);
        root.info(MarkerManager.getMarker("EVENT"), msg);
    }
