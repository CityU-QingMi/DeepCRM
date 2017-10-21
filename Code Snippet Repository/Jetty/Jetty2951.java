    @Override
    public void setStatus(int sc)
    {
        if (sc <= 0)
            throw new IllegalArgumentException();
        if (!isIncluding())
        {
            _status = sc;
            _reason = null;
        }
    }
