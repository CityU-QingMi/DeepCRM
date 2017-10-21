      @Override
      protected boolean updateMembers(Session s, ThreadLocalRandom random, Family f) {
         int numMembers = f.getMembers().size();
         if (numMembers > 0) {
            Iterator<Person> it = f.getMembers().iterator();
            Person person = null;
            for (int i = random.nextInt(numMembers); i >= 0; --i) {
               person = it.next();
            }
            it.remove();
            if (person != null) {
               s.delete(person);
            }
            return true;
         } else {
            return false;
         }
      }
