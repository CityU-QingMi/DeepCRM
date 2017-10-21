   Operation findUpdatedOperation(final List<Integer> updatedIdsSeq) {
      return new Operation("FIND_UPDATED") {
         @Override
         boolean call(final int run) throws Exception {
            return captureThrowables(new Callable<Boolean>() {
               @Override
               public Boolean call() throws Exception {
                  Session s = sessionFactory.openSession();

                  int id = updatedIdsSeq.get(RANDOM.nextInt(
                        updatedIdsSeq.size()));
                  Family family = (Family) s.load(Family.class, id);
                  String secondName = family.getSecondName();
                  assertNotNull(secondName);
                  assertTrue("Second name not expected: " + secondName,
                        secondName.startsWith("Arrizabalaga"));

                  s.close();
                  return true;
               }
            });
         }
      };
   }
