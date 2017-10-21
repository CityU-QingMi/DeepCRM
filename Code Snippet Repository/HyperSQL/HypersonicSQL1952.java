    public void privlog(Level level, String message, Throwable t,
                        int revertMethods, Class skipClass) {

        if (noopMode) {
            return;
        }

        if (log4jLogger == null) {
            StackTraceElement[] elements = new Throwable().getStackTrace();
            String            c          = "";
            String            m          = "";

            if (elements.length > revertMethods) {
                c = elements[revertMethods].getClassName();
                m = elements[revertMethods].getMethodName();
            }

            if (t == null) {
                jdkLogger.logp(level, c, m, message);
            } else {
                jdkLogger.logp(level, c, m, message, t);
            }
        } else {
            try {
                log4jLogMethod.invoke(log4jLogger, callerFqcnAvailable
                         ? new Object[] {
                            skipClass.getName(), jdkToLog4jLevels.get(level),
                            message, t}
                         : new Object[] {
                            jdkToLog4jLevels.get(level), message, t}
                );
            } catch (Exception e) {
                throw new RuntimeException(
                    "Logging failed when attempting to log: " + message, e);
            }
        }
    }
