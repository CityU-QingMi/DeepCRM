    public void setProtectedTargets (String[] targets)
    {
        if (targets == null)
        {
            _protectedTargets = null;
            return;
        }

        _protectedTargets = Arrays.copyOf(targets, targets.length);
    }
