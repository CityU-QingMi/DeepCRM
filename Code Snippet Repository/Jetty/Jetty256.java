    public boolean advance()
    {
        if (iterator instanceof Synchronizable)
        {
            synchronized (((Synchronizable)iterator).getLock())
            {
                return advance(iterator);
            }
        }
        else
        {
            return advance(iterator);
        }
    }
