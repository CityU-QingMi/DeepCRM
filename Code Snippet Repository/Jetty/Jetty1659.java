    @Override
    public ByteBuffer acquire(int size, boolean direct)
    {
        ByteBuffer buffer = delegate.acquire(size, direct);
        boolean leaked = leakDetector.acquired(buffer);
        if (NOISY || !leaked)
        {
            leakedAcquires.incrementAndGet();
            LOG.info(String.format("ByteBuffer acquire %s leaked.acquired=%s", leakDetector.id(buffer), leaked ? "normal" : "LEAK"),
                    new Throwable("LeakStack.Acquire"));
        }
        return buffer;
    }
