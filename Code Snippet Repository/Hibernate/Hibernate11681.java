   private Operation findRandomOperation() {
      return new Operation("FIND_RANDOM") {
         @Override
         boolean call(final int run) throws Exception {
            return captureThrowables(new Callable<Boolean>() {
               @Override
               public Boolean call() throws Exception {
                  Session s = sessionFactory.openSession();

                  int id = RANDOM.nextInt(numEntities) + 1;
                  Family family = (Family) s.load(Family.class, id);
                  String familyName = family.getName();
                  // Skip ñ check in order to avoid issues...
                  assertTrue("Unexpected family: " + familyName ,
                        familyName.startsWith("Zamarre"));

                  s.close();
                  return true;
               }
            });
         }
      };
   }
