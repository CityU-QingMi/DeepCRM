        @Override
        public Set<String> doGetExpired(Set<String> candidates)
        {
            Set<String> expiredIds = new HashSet<>();
            long now = System.currentTimeMillis();
            if (candidates != null)
            {
                for (String id:candidates)
                {
                    SessionData sd = _store.get(id);
                    if (sd == null)
                        expiredIds.add(id);
                    else if (sd.isExpiredAt(now))
                        expiredIds.add(id);
                }
            }
            
            for (String id:_store.keySet())
            {
                SessionData sd = _store.get(id);
                if (sd.isExpiredAt(now))
                    expiredIds.add(id);
            }
            
            return expiredIds;
        }
