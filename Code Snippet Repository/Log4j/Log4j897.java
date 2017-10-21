    @Override
    public Throwable getThrown() {
        // after deserialization, thrown is null but thrownProxy may be non-null
        if (thrown == null) {
            if (thrownProxy != null) {
                thrown = thrownProxy.getThrowable();
            }
        }
        return thrown;
    }
