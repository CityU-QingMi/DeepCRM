    private AppEntry findAppByOriginId(String originId)
    {
        if (originId == null)
        {
            return null;
        }

        for (AppEntry entry : _apps)
        {
            if (originId.equals(entry.app.getOriginId()))
            {
                return entry;
            }
        }
        return null;
    }
