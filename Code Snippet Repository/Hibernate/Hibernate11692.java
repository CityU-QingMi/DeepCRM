   Operation insertOperation() {
      return new Operation("INSERT") {
         @Override
         boolean call(final int run) throws Exception {
            return captureThrowables(new Callable<Boolean>() {
               @Override
               public Boolean call() throws Exception {
                  return withTx(tm, new Callable<Boolean>() {
                     @Override
                     public Boolean call() throws Exception {
                        Session s = sessionFactory.openSession();
                        s.getTransaction().begin();

                        String name = "Zamarreño-" + run;
                        Family family = new Family(name);
                        s.persist(family);

                        s.getTransaction().commit();
                        s.close();
                        return true;
                     }
                  });
               }
            });
         }
      };
   }
