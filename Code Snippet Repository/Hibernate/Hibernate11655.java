   private <T> void dumpLogs(String prefix, List<Log<T>> logs) {
      try {
         File f = File.createTempFile(prefix,  ".log");
         log.info("Dumping logs into " + f.getAbsolutePath());
         try (BufferedWriter writer = Files.newBufferedWriter(f.toPath())) {
            for (Log<T> log : logs) {
               writer.write(log.toString());
               writer.write('\n');
            }
         }
      } catch (IOException e) {
         log.error("Failed to dump family logs");
      }
   }
