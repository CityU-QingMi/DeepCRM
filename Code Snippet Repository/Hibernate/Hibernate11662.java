      @Override
      public void run() throws Exception {
         Ref<Set<String>> newMembers = new Ref<>();
         withRandomFamily((s, f) -> {
            boolean updated = updateMembers(s, ThreadLocalRandom.current(), f);
            if (updated) {
               newMembers.set(membersToNames(f.getMembers()));
               s.persist(f);
            }
         }, Ref.empty(), newMembers, LockMode.OPTIMISTIC_FORCE_INCREMENT);
      }
