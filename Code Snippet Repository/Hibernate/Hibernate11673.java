   private void iterateInvalidators(List<DelayedInvalidators> delayed, Method getInvalidators, BiConsumer<Object, java.util.Collection> invalidatorConsumer) throws IllegalAccessException, InvocationTargetException {
      for (Iterator<DelayedInvalidators> iterator = delayed.iterator(); iterator.hasNext(); ) {
         DelayedInvalidators entry = iterator.next();
         Object pendingPutMap = entry.getPendingPutMap();
         if (pendingPutMap == null) {
            iterator.remove();
         }
         else {
            java.util.Collection invalidators = (java.util.Collection) getInvalidators.invoke(pendingPutMap);
            if (invalidators == null || invalidators.isEmpty()) {
               iterator.remove();
            }
            invalidatorConsumer.accept(entry.key, invalidators);
         }
      }
   }
