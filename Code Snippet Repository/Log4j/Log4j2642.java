    @Override
    public void append(final LogEvent event) {
        try {
            for (int i = 0; i < 50; i++) {
                Thread.sleep(10);
                thing.doSomething();
            }
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.print("Log: " + getLayout().toSerializable(event));
    }
