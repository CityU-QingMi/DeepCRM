    protected boolean add(Entry entry)
    {
        if (_entries.containsKey(entry.getPattern()))
            return false;
        _entries.put(entry.getPattern(),entry);

        if (entry instanceof LocationEntry || entry instanceof ModuleEntry)
        {
            if (entry.isInclusive())
                _locations.include(entry);
            else
                _locations.exclude(entry);
        }
        else
        {
            if (entry.isInclusive())
                _patterns.include(entry);
            else
                _patterns.exclude(entry);
        }
        return true;
    }
