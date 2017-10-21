   private <T> NavigableMap<Integer, List<Log<T>>> getWritesAtTime(List<Log<T>> list) {
      NavigableMap<Integer, List<Log<T>>> writes = new TreeMap<>();
      for (Log log : list) {
         if (log.type != LogType.WRITE) continue;
         for (int time = log.before; time <= log.after; ++time) {
            List<Log<T>> onTime = writes.get(time);
            if (onTime == null) {
               writes.put(time, onTime = new ArrayList<>());
            }
            onTime.add(log);
         }
      }
      return writes;
   }
