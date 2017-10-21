    public void reset(final long value)
    {
        _current.set(value);
        _total.reset();
        _max.reset();
        if (value>0)
        {
            _total.add(value);
            _max.accumulate(value);
        }
    }
