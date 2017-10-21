   private void store() throws Exception {
      for (int i = 0; i < NUM_INSTANCES; i++) {
         final Age age = new Age();
         age.setAge(i);
         withTx(tm, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
               Session s = sessionFactory.openSession();
               s.getTransaction().begin();
               s.persist(age);
               s.getTransaction().commit();
               s.close();
               return null;
            }
         });
      }
   }
