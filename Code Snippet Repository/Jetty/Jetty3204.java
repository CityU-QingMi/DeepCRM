    @Override
    public Enumeration<String> getAttributeNames()
    {
        try (Lock lock = _lock.lock())
        {
            checkValidForRead();
            final Iterator<String> itor = _sessionData.getKeys().iterator();
            return new Enumeration<String> ()
            {

                @Override
                public boolean hasMoreElements()
                {
                    return itor.hasNext();
                }

                @Override
                public String nextElement()
                {
                    return itor.next();
                }

            };
        }
    }
