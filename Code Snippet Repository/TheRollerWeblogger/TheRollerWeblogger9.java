    @Override
    public void printStackTrace(PrintWriter s) {
        // just print our message since we know this exception should be wrapping
        // a more detailed exception from whatever fetching solution is used
        s.println(super.getMessage());
        if (getRootCause() != null) {
            s.println("--- ROOT CAUSE ---");
            getRootCause().printStackTrace(s);
        }
    }
