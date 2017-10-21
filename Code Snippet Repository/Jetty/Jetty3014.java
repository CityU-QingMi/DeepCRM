    public void setAvailable(boolean available)
    {
        synchronized (this)
        {
            if (available && isRunning())
                _availability = Availability.AVAILABLE;
            else if (!available || !isRunning())
                _availability = Availability.UNAVAILABLE;
        }
    }
