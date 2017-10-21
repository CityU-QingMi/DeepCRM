    public synchronized ThreadFactory setImpl(ThreadFactory f) {

        ThreadFactory old;

        old     = factory;
        factory = (f == null) ? this
                              : f;

        return old;
    }
