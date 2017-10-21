    @Deprecated
    @Override
    public String[] getValueNames() throws IllegalStateException
    {
        try (Lock lock = _lock.lock())
        {
            checkValidForRead();
            Iterator<String> itor = _sessionData.getKeys().iterator();
            if (!itor.hasNext())
                return new String[0];
            ArrayList<String> names = new ArrayList<String>();
            while (itor.hasNext())
                names.add(itor.next());
            return names.toArray(new String[names.size()]);
        }
    }
