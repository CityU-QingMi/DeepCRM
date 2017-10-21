    public void setStatusWithReason(int sc, String sm)
    {
        if (sc <= 0)
            throw new IllegalArgumentException();
        if (!isIncluding())
        {
            _status = sc;
            _reason = sm;
        }
    }
