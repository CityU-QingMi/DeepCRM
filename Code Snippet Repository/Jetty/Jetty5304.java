    public long add(final long delta)
    {
        long value=_current.addAndGet(delta);
        if (delta > 0)
        {
            _total.add(delta);
            _max.accumulate(value);
        }
        return value;
    }
