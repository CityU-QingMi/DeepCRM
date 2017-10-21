    public boolean isLocked()
    {
       if ((_env.get(LOCK_PROPERTY) == null) && (_env.get(UNLOCK_PROPERTY) == null))
           return false;

       Object lockKey = _env.get(LOCK_PROPERTY);
       Object unlockKey = _env.get(UNLOCK_PROPERTY);

       if ((lockKey != null) && (unlockKey != null) && (lockKey.equals(unlockKey)))
           return false;
       return true;
    }
