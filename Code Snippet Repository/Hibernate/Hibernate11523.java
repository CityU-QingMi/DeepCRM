   @Test
   public void testConstruction() {
      StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
              .applySetting( AvailableSettings.CACHE_REGION_FACTORY, JndiInfinispanRegionFactory.class.getName() )
              .build();
      try {
         RegionFactory regionFactory = ssr.getService( RegionFactory.class );
         assertTyping( JndiInfinispanRegionFactory.class, regionFactory );
      }
      finally {
         StandardServiceRegistryBuilder.destroy( ssr );
      }
   }
