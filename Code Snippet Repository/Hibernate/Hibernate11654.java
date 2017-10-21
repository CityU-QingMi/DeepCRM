   private <T> void checkCorrectness(String dumpPrefix, List<Log<T>> logs, NavigableMap<Integer, List<Log<T>>> writesByTime) {
      Collections.sort(logs, WALL_CLOCK_TIME_COMPARATOR);
      int nullReads = 0, reads = 0, writes = 0;
      for (Log read : logs) {
         if (read.type != LogType.READ) {
            writes++;
            continue;
         }
         if (read.getValue() == null || isEmptyCollection(read)) nullReads++;
         else reads++;

         Map<T, Log<T>> possibleValues = new HashMap<>();
         for (List<Log<T>> list : writesByTime.subMap(read.before, true, read.after, true).values()) {
            for (Log<T> write : list) {
               if (read.precedes(write)) continue;
               possibleValues.put(write.getValue(), write);
            }
         }
         int startOfLastWriteBeforeRead = 0;
         for (Map.Entry<Integer, List<Log<T>>> entry : writesByTime.headMap(read.before, false).descendingMap().entrySet()) {
            int time = entry.getKey();
            if (time < startOfLastWriteBeforeRead) break;
            for (Log<T> write : entry.getValue()) {
               if (write.after < read.before && write.before > startOfLastWriteBeforeRead) {
                  startOfLastWriteBeforeRead = write.before;
               }
               possibleValues.put(write.getValue(), write);
            }
         }

         if (possibleValues.isEmpty()) {
            // the entry was not created at all (first write failed)
            break;
         }
         if (!possibleValues.containsKey(read.getValue())) {
            dumpLogs(dumpPrefix, logs);
            exceptions.add(new IllegalStateException(String.format("R %s: %d .. %d (%s, %s) -> %s not in %s (%d+)", dumpPrefix,
                  read.before, read.after, read.threadName, new SimpleDateFormat("HH:mm:ss,SSS").format(new Date(read.wallClockTime)),
                  read.getValue(), possibleValues.values(), startOfLastWriteBeforeRead)));
            break;
         }
      }
      log.infof("Checked %d null reads, %d reads and %d writes%n", nullReads, reads, writes);
   }
