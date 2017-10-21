    public String getExceptionStack() {
        String exceptionStack = null;

        if (getException() != null) {
            try (StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw)) {
                getException().printStackTrace(pw);
                exceptionStack = sw.toString();
            } catch (IOException e) {
                // Ignore exception generating stack trace.
            }
        }

        return exceptionStack;
    }
