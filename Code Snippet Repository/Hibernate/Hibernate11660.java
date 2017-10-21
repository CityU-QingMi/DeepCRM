      @Override
      public void run() throws Exception {
         Family family = createFamily();
         int before = timestampGenerator.getAndIncrement();
         log.trace("Started InsertFamily at " + before);
         boolean failure = false;
         try {
            withSession(s -> s.persist(family));
         } catch (Exception e) {
            failure = true;
            throw e;
         } finally {
            int after = timestampGenerator.getAndIncrement();
            log.trace("Finished InsertFamily at " + after + ", " + (failure ? "failed" : "success"));
            familyIds.put(family.getId(), new AtomicInteger(NUM_ACCESS_AFTER_REMOVAL));
            LogType type = failure || rolledBack ? LogType.WRITE_FAILURE : LogType.WRITE;
            getRecordList(familyNames, family.getId()).add(new Log<>(before, after, family.getName(), type));
            getRecordList(familyMembers, family.getId()).add(new Log<>(before, after, membersToNames(family.getMembers()), type));
         }
      }
