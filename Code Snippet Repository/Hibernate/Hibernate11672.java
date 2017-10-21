   protected void checkForEmptyPendingPuts() throws Exception {
      Field pp = PutFromLoadValidator.class.getDeclaredField("pendingPuts");
      pp.setAccessible(true);
      Method getInvalidators = null;
      List<DelayedInvalidators> delayed = new LinkedList<>();
      for (int i = 0; i < sessionFactories.length; i++) {
         SessionFactoryImplementor sfi = (SessionFactoryImplementor) sessionFactories[i];
         for (Object regionName : sfi.getCache().getSecondLevelCacheRegionNames()) {
            PutFromLoadValidator validator = getPutFromLoadValidator(sfi, (String) regionName);
            if (validator == null) {
               log.warn("No validator for " + regionName);
               continue;
            }
            ConcurrentMap<Object, Object> map = (ConcurrentMap) pp.get(validator);
            for (Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
               Map.Entry entry = iterator.next();
               if (getInvalidators == null) {
                  getInvalidators = entry.getValue().getClass().getMethod("getInvalidators");
                  getInvalidators.setAccessible(true);
               }
               java.util.Collection invalidators = (java.util.Collection) getInvalidators.invoke(entry.getValue());
               if (invalidators != null && !invalidators.isEmpty()) {
                  delayed.add(new DelayedInvalidators(map, entry.getKey()));
               }
            }
         }
      }
      // poll until all invalidations come
      long deadline = System.currentTimeMillis() + 30000;
      while (System.currentTimeMillis() < deadline) {
         iterateInvalidators(delayed, getInvalidators, (k, i) -> {});
         if (delayed.isEmpty()) {
            break;
         }
         Thread.sleep(1000);
      }
      if (!delayed.isEmpty()) {
         iterateInvalidators(delayed, getInvalidators, (k, i) -> log.warnf("Left invalidators on key %s: %s", k, i));
         throw new IllegalStateException("Invalidators were not cleared: " + delayed.size());
      }
   }
