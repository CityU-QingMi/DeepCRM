    public void log(final LogEvent event) {
        beforeLogEvent();
        try {
            if (!isFiltered(event)) {
                processLogEvent(event);
            }
        } finally {
            afterLogEvent();
        }
    }
