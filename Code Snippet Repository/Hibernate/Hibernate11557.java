	 @Test
	 public void testEqualityFromType() throws Exception {
		  Person john = new Person("John", "Black", 26);
		  Person peter = new Person("Peter", "White", 32);

		  withTxSession(s -> {
				s.persist(john);
				s.persist(peter);
		  });

		  Statistics statistics = sessionFactory().getStatistics();
		  statistics.clear();

		  for (int i = 0; i < 5; ++i) {
				withTxSession(s -> {
					 Person p1 = s.get(Person.class, john.getName());
					 assertPersonEquals(john, p1);
					 Person p2 = s.get(Person.class, peter.getName());
					 assertPersonEquals(peter, p2);
					 Person p3 = s.get(Person.class, new Name("Foo", "Bar"));
					 assertNull(p3);
				});
		  }

		  assertTrue(statistics.getSecondLevelCacheHitCount() > 0);
		  assertTrue(statistics.getSecondLevelCacheMissCount() > 0);
	 }
