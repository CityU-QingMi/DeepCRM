    @Test
    public void testShutdownWithSleepReturnsAfterAllThreadsAreStopped() throws Exception {
      Map<Thread, StackTraceElement[]> allThreadsStart = Thread.getAllStackTraces();
      int threadPoolSize = 5;
      Scheduler scheduler = createScheduler("testShutdownWithSleepReturnsAfterAllThreadsAreStopped", threadPoolSize);
      
      Thread.sleep(500L);
      
      Map<Thread, StackTraceElement[]> allThreadsRunning = Thread.getAllStackTraces();

      scheduler.shutdown( true );
      
      Thread.sleep(200L);

      Map<Thread, StackTraceElement[]> allThreadsEnd = Thread.getAllStackTraces();
      Set<Thread> endingThreads = new HashSet<Thread>(allThreadsEnd.keySet());
      // remove all pre-existing threads from the set
      for(Thread t: allThreadsStart.keySet()) {
        allThreadsEnd.remove(t);
      }
      // remove threads that are known artifacts of the test
      for(Thread t: endingThreads) {
        if(t.getName().contains("derby") && t.getThreadGroup().getName().contains("derby")) {
          allThreadsEnd.remove(t);
        }
        if(t.getThreadGroup() != null && t.getThreadGroup().getName().equals("system")) {
          allThreadsEnd.remove(t);
          
        }
        if(t.getThreadGroup() != null && t.getThreadGroup().getName().equals("main")) {
          allThreadsEnd.remove(t);
        }
      }
      if(allThreadsEnd.size() > 0) {
        // log the additional threads
        for(Thread t: allThreadsEnd.keySet()) {
          System.out.println("*** Found additional thread: " + t.getName() + " (of type " + t.getClass().getName() +")  in group: " + t.getThreadGroup().getName() + " with parent group: " + (t.getThreadGroup().getParent() == null ? "-none-" : t.getThreadGroup().getParent().getName()));
        }          
        // log all threads that were running before shutdown
        for(Thread t: allThreadsRunning.keySet()) {
          System.out.println("- Test runtime thread: " + t.getName() + " (of type " + t.getClass().getName() +")  in group: " + (t.getThreadGroup() == null ? "-none-" : (t.getThreadGroup().getName() + " with parent group: " + (t.getThreadGroup().getParent() == null ? "-none-" : t.getThreadGroup().getParent().getName()))));
        }          
      }
      assertTrue( "Found unexpected new threads (see console output for listing)", allThreadsEnd.size() == 0  );
    }
