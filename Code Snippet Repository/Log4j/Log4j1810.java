    private static String getStackTrace(final Throwable throwable) {
        String returnValue = throwable.toString() + '\n';

        for (final StackTraceElement element : throwable.getStackTrace()) {
            returnValue += "\tat " + element.toString() + '\n';
        }

        if (throwable.getCause() != null) {
            returnValue += "Caused by " + getStackTrace(throwable.getCause());
        }

        return returnValue;
    }
