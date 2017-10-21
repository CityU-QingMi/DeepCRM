   private void test(String cacheKeysFactory, String keyClassName) throws Exception {
      SessionFactory sessionFactory = getSessionFactory(cacheKeysFactory);
      withTxSession(false, sessionFactory, s -> {
         Person person = new Person("John", "Black", 39);
         s.persist(person);
      });

      TestInfinispanRegionFactory regionFactory = (TestInfinispanRegionFactory) ((CacheImplementor) sessionFactory.getCache()).getRegionFactory();
      Cache<Object, Object> cache = regionFactory.getCacheManager().getCache(Person.class.getName());
      Iterator<Object> iterator = cache.getAdvancedCache().getDataContainer().keySet().iterator();
      assertTrue(iterator.hasNext());
      Object key = iterator.next();
      assertEquals(keyClassName, key.getClass().getSimpleName());

      withTxSession(false, sessionFactory, s -> {
         Person person = s.load(Person.class, new Name("John", "Black"));
         assertEquals(39, person.getAge());
      });
   }
