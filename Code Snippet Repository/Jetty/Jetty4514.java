    public boolean enable(String source,boolean transitive)
    {
        boolean updated=_enables.isEmpty();
        if (transitive)
        {
            // Ignore transitive selections if explicitly enabled
            if (!_notTransitive)
                _enables.add(source);
        }
        else
        {
            if (!_notTransitive)
            {
                // Ignore transitive selections if explicitly enabled
                updated=true;
                _enables.clear(); // clear any transitive enabling
            }
            _notTransitive=true;
            _enables.add(source);
        }
        return updated;
    }
