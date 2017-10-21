    public void set(final long sample)
    {
        long total = _total.addAndGet(sample);
        long count = _count.incrementAndGet();

        if (count>1)
        {
            long mean10 = total*10/count;
            long delta10 = sample*10 - mean10;
            _totalVariance100.add(delta10*delta10);
        }

        _max.accumulate(sample);
    }
