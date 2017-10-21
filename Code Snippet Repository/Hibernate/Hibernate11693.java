   Operation updateOperation() {
      return new Operation("UPDATE") {
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

                        // Update random entity that has been inserted
                        int id = RANDOM.nextInt(numEntities) + 1;
                        Family family = (Family) s.load(Family.class, id);
                        String newSecondName = "Arrizabalaga-" + run;
                        family.setSecondName(newSecondName);

                        s.getTransaction().commit();
                        s.close();
                        // Cache updated entities for later read
                        updatedIds.add(id);
                        return true;
                     }
                  });
               }
            });
         }
      };
   }
