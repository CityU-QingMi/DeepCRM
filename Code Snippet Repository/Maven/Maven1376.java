    @Override
    public List<String> getInactiveProfileIds()
    {
        if ( inactiveProfileIds == null )
        {
            inactiveProfileIds = new ArrayList<>();
        }

        return inactiveProfileIds;
    }
