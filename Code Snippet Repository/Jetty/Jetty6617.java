    @Override
    public void resume()
    {
        if (suspendToken.getAndSet(false))
        {
            if (!isReading())
            {
                fillInterested();
            }
        }
    }
