    @Override
    public Throwable getThrowable() {
        if (throwable != null) {
            return throwable;
        }
        if (message == null) {
            message = getMessage(messagePattern, argArray, null);
        }
        return message.getThrowable();
    }
