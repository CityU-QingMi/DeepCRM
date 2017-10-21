      protected void withRandomFamilies(int numFamilies, BiConsumer<Session, Family[]> consumer, String[] familyNameUpdates, Set<String>[] familyMembersUpdates, LockMode lockMode) throws Exception {
         int ids[] = new int[numFamilies];
         Log<String>[] familyNameLogs = new Log[numFamilies];
         Log<Set<String>>[] familyMembersLogs = new Log[numFamilies];
         for (int i = 0; i < numFamilies; ++i) {
            ids[i] = randomFamilyId(ThreadLocalRandom.current());
            familyNameLogs[i] = new Log<>();
            familyMembersLogs[i] = new Log<>();
         }
         int before = timestampGenerator.getAndIncrement();
         log.tracef("Started %s(%s) at %d", getClass().getSimpleName(), Arrays.toString(ids), before);

         boolean failure = false;
         try {
            withSession(s -> {
               Family[] families = new Family[numFamilies];
               for (int i = 0; i < numFamilies; ++i) {
                  Family f = lockMode != null ? s.get(Family.class, ids[i], lockMode) : s.get(Family.class, ids[i]);
                  families[i] = f;
                  if (f == null) {
                     familyNameLogs[i].setValue(null);
                     familyMembersLogs[i].setValue(Collections.EMPTY_SET);
                     familyNotFound(ids[i]);
                  } else {
                     familyNameLogs[i].setValue(f.getName());
                     familyMembersLogs[i].setValue(membersToNames(f.getMembers()));
                  }
               }
               consumer.accept(s, families);
            });
         } catch (Exception e) {
            failure = true;
            throw e;
         } finally {
            int after = timestampGenerator.getAndIncrement();
            for (int i = 0; i < numFamilies; ++i) {
               recordReadWrite(ids[i], before, after, failure,
                     familyNameUpdates != null ? Ref.of(familyNameUpdates[i]) : Ref.empty(),
                     familyMembersUpdates != null ? Ref.of(familyMembersUpdates[i]) : Ref.empty(),
                     familyNameLogs[i], familyMembersLogs[i]);
            }
         }
      }
