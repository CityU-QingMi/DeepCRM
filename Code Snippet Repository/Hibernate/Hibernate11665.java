      @Override
      public void run() throws Exception {
         String prefix = new StringBuilder(2)
               .append((char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1)).append('%').toString();
         int[] ids = new int[MAX_RESULTS];
         String[] names = new String[MAX_RESULTS];
         Set<String>[] members = new Set[MAX_RESULTS];

         int before = timestampGenerator.getAndIncrement();
         log.tracef("Started QueryFamilies at %d", before);
         withSession(s -> {
            List<Family> results = s.createCriteria(Family.class)
                  .add(Restrictions.like("name", prefix))
                  .setMaxResults(MAX_RESULTS)
                  .setCacheable(true)
                  .list();
            int index = 0;
            for (Family f : results) {
               ids[index] = f.getId();
               names[index] = f.getName();
               members[index] = membersToNames(f.getMembers());
               ++index;
            }
         });

         int after = timestampGenerator.getAndIncrement();
         log.tracef("Finished QueryFamilies at %d", after);
         for (int index = 0; index < MAX_RESULTS; ++index) {
            if (names[index] == null) break;
            getRecordList(familyNames, ids[index]).add(new Log<>(before, after, names[index], LogType.READ));
            getRecordList(familyMembers, ids[index]).add(new Log<>(before, after, members[index], LogType.READ));
         }
      }
