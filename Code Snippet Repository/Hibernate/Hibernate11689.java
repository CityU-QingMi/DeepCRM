   void doEntityLifecycle(boolean isWarmup) {
      long runningTimeout = isWarmup ? WARMUP_TIME : RUNNING_TIME;
      TotalStats insertPerf = runEntityInsert(runningTimeout);
      numEntities = countEntities().intValue();
      printResult(isWarmup, "[provider=%s] Inserts/s %10.2f (%d entities)\n",
            provider, insertPerf.getOpsPerSec("INSERT"), numEntities);

      TotalStats updatePerf = runEntityUpdate(runningTimeout);
      List<Integer> updateIdsSeq = new ArrayList<Integer>(updatedIds);
      printResult(isWarmup, "[provider=%s] Updates/s %10.2f (%d updates)\n",
            provider, updatePerf.getOpsPerSec("UPDATE"), updateIdsSeq.size());

      TotalStats findUpdatedPerf =
            runEntityFindUpdated(runningTimeout, updateIdsSeq);
      printResult(isWarmup, "[provider=%s] Updated finds/s %10.2f\n",
            provider, findUpdatedPerf.getOpsPerSec("FIND_UPDATED"));

      TotalStats findQueryPerf = runEntityFindQuery(runningTimeout, isWarmup);
      printResult(isWarmup, "[provider=%s] Query finds/s %10.2f\n",
            provider, findQueryPerf.getOpsPerSec("FIND_QUERY"));

      TotalStats findRandomPerf = runEntityFindRandom(runningTimeout);
      printResult(isWarmup, "[provider=%s] Random finds/s %10.2f\n",
            provider, findRandomPerf.getOpsPerSec("FIND_RANDOM"));

      // Get all entity ids
      List<Integer> entityIds = new ArrayList<Integer>();
      for (int i = 1; i <= numEntities; i++) entityIds.add(i);

      // Shuffle them
      Collections.shuffle(entityIds);

      // Add them to the queue delete consumption
      removeIds.addAll(entityIds);

      TotalStats deletePerf = runEntityDelete(runningTimeout);
      printResult(isWarmup, "[provider=%s] Deletes/s %10.2f\n",
            provider, deletePerf.getOpsPerSec("DELETE"));

      // TODO Print 2LC statistics...
   }
