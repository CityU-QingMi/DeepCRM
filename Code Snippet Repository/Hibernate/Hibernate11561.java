   protected void assertNoInvalidators(AdvancedCache<Object, Object> pendingPutsCache) throws Exception {
      Method getInvalidators = null;
      for (Map.Entry<Object, Object> entry : pendingPutsCache.entrySet()) {
         if (getInvalidators == null) {
            getInvalidators = entry.getValue().getClass().getMethod("getInvalidators");
            getInvalidators.setAccessible(true);
         }
         Collection invalidators = (Collection) getInvalidators.invoke(entry.getValue());
         if (invalidators != null) {
            assertTrue("Invalidators on key " + entry.getKey() + ": " + invalidators, invalidators.isEmpty());
         }
      }
   }
