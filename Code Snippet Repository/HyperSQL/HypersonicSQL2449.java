    private void logSevereEvent(String message, Throwable t, long position) {

        if (logEvents) {
            StringBuffer sb = new StringBuffer(message);

            sb.append(' ').append(position);

            message = sb.toString();

            database.logger.logSevereEvent(message, t);
        }
    }
