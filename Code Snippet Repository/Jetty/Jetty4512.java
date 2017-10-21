    public boolean clearTransitiveEnable()
    {
        if (_notTransitive)
            throw new IllegalStateException("Not Transitive");
        if (isEnabled())
        {
            _enables.clear();
            return true;
        }
        return false;
    }
