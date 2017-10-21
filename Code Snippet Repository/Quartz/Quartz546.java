    private void handleError(String message, Exception e) throws SchedulerException {
        if (isThrowIfPropertyNotFound()) {
            throw new SchedulerException(message, e);
        }
        
        if (isWarnIfPropertyNotFound()) {
            if (e == null) {
                getLog().warn(message);
            } else {
                getLog().warn(message, e);
            }
        }
    }
