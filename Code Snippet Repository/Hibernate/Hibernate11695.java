   private Operation findQueryOperation(final boolean isWarmup) {
      return new Operation("FIND_QUERY") {
         @Override
         boolean call(final int run) throws Exception {
            return captureThrowables(new Callable<Boolean>() {
               @Override
               public Boolean call() throws Exception {
                  Session s = sessionFactory.openSession();

                  Query query = s.createQuery("from Family")
                        .setCacheable(true);
                  int maxResults = isWarmup ? 10 : 100;
                  query.setMaxResults(maxResults);
                  List<Family> result = (List<Family>) query.list();
                  assertEquals(maxResults, result.size());

                  s.close();
                  return true;
               }
            });
         }
      };
   }
