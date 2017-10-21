   Long countEntities() {
      try {
         return withTx(tm, new Callable<Long>() {
            @Override
            public Long call() throws Exception {
               Session s = sessionFactory.openSession();
               Query query = s.createQuery("select count(*) from Family");
               Object result = query.list().get(0);
               s.close();
               return (Long) result;
            }
         });
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
