   private Operation deleteOperation() {
      return new Operation("DELETE") {
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

                        // Get each id and remove it
                        int id = removeIds.poll();
                        Family family = (Family) s.load(Family.class, id);
                        String familyName = family.getName();
                        // Skip ñ check in order to avoid issues...
                        assertTrue("Unexpected family: " + familyName ,
                              familyName.startsWith("Zamarre"));
                        s.delete(family);

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
