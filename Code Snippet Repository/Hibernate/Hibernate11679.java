      private void queryItems() throws Exception {
         withTx(tm, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
               Session s = sessionFactory.getCurrentSession();
               Query query = s.getNamedQuery(Age.QUERY).setCacheable(true);
               List<Age> result = (List<Age>) query.list();
               assertFalse(result.isEmpty());
               return null;
            }
         });
      }
