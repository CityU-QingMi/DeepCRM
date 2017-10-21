    @Override
    public Set<String> checkExpiration(Set<String> candidates)
    {
       if (!isStarted())
           return Collections.emptySet();
       
       if (LOG.isDebugEnabled())
           LOG.debug("{} checking expiration on {}", this, candidates);
       Set<String> allCandidates = _sessionDataStore.getExpired(candidates);
       Set<String> sessionsInUse = new HashSet<>();
       if (allCandidates != null)
       {
           for (String c:allCandidates)
           {
               Session s = doGet(c);
               if (s != null && s.getRequests() > 0) //if the session is in my cache, check its not in use first
                   sessionsInUse.add(c);
           }
           try
           {
               allCandidates.removeAll(sessionsInUse);
           }
           catch (UnsupportedOperationException e)
           {
               Set<String> tmp = new HashSet<>(allCandidates);
               tmp.removeAll(sessionsInUse);
               allCandidates = tmp;
           }
       }
       return allCandidates;
    }
