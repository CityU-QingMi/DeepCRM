      @Override
      public void run() throws Exception {
         String[] newNames = new String[numUpdates];
         for (int i = 0; i < numUpdates; ++i) {
            newNames[i] = randomString(ThreadLocalRandom.current());
         }
         withRandomFamilies(numUpdates, (s, families) -> {
            for (int i = 0; i < numUpdates; ++i) {
               Family f = families[i];
               if (f != null) {
                  f.setName(newNames[i]);
                  s.persist(f);
               }
            }
         }, newNames, null, LockMode.OPTIMISTIC_FORCE_INCREMENT);
      }
