   @BeforeClassOnce
   public void setup() {
      executor = Executors.newCachedThreadPool(new ThreadFactory() {
         AtomicInteger counter = new AtomicInteger();

         @Override
         public Thread newThread(Runnable r) {
            return new Thread(r, "Executor-" +  counter.incrementAndGet());
         }
      });
   }
