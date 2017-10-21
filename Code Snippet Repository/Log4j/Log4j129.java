    @Override
    public Throwable getThrowable() {
        for (StructuredDataMessage msg : structuredDataMessageList) {
            Throwable t = msg.getThrowable();
            if (t != null) {
                return t;
            }
        }
        return null;
    }
