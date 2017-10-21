      protected void withRandomFamily(BiConsumer<Session, Family> consumer, Ref<String> familyNameUpdate, Ref<Set<String>> familyMembersUpdate, LockMode lockMode) throws Exception {
         int id = randomFamilyId(ThreadLocalRandom.current());
         int before = timestampGenerator.getAndIncrement();
         log.tracef("Started %s(%d, %s) at %d", getClass().getSimpleName(), id, rolledBack, before);
         Log<String> familyNameLog = new Log<>();
         Log<Set<String>> familyMembersLog = new Log<>();

         boolean failure = false;
         try {
            withSession(s -> {
               Family f = lockMode != null ? s.get(Family.class, id, lockMode) : s.get(Family.class, id);
               if (f == null) {
                  familyNameLog.setValue(null);
                  familyMembersLog.setValue(Collections.EMPTY_SET);
                  familyNotFound(id);
               } else {
                  familyNameLog.setValue(f.getName());
                  familyMembersLog.setValue(membersToNames(f.getMembers()));
                  consumer.accept(s, f);
               }
            });
         } catch (Exception e) {
            failure = true;
            throw e;
         } finally {
            int after = timestampGenerator.getAndIncrement();
            recordReadWrite(id, before, after, failure, familyNameUpdate, familyMembersUpdate, familyNameLog, familyMembersLog);
         }
      }
