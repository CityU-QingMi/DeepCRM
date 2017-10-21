    public void log2(final LogEvent event) {
        beforeLogEvent();
        try {
            if (!isFiltered(event)) {
                processLogEvent(event);
            }
        } finally {
            afterLogEvent2();
        }
    }
