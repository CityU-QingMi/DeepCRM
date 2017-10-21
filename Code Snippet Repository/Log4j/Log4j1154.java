    public void clear() {
        loggerFqcn = null;
        marker = null;
        level = null;
        loggerName = null;
        message = null;
        thrown = null;
        thrownProxy = null;
        source = null;
        if (contextData != null) {
            if (contextData.isFrozen()) { // came from CopyOnWrite thread context
                contextData = null;
            } else {
                contextData.clear();
            }
        }
        contextStack = null;

        // ThreadName should not be cleared: this field is set in the ReusableLogEventFactory
        // where this instance is kept in a ThreadLocal, so it usually does not change.
        // threadName = null; // no need to clear threadName

        // ensure that excessively long char[] arrays are not kept in memory forever
        StringBuilders.trimToMaxSize(messageText, Constants.MAX_REUSABLE_MESSAGE_SIZE);

        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                parameters[i] = null;
            }
        }

        // primitive fields that cannot be cleared:
        //timeMillis;
        //threadId;
        //threadPriority;
        //includeLocation;
        //endOfBatch;
        //nanoTime;
    }
