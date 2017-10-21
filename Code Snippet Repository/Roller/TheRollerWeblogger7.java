    @Override
    public void printStackTrace() {
        // just print our message since we know this exception should be wrapping
        // a more detailed exception from whatever fetching solution is used
        System.out.println(super.getMessage());
        if (getRootCause() != null) {
            System.out.println("--- ROOT CAUSE ---");
            getRootCause().printStackTrace();
        }
    }
