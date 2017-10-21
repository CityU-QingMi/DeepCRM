   private PutFromLoadValidator getPutFromLoadValidator(SessionFactoryImplementor sfi, String regionName) throws NoSuchFieldException, IllegalAccessException {
      RegionAccessStrategy strategy = sfi.getSecondLevelCacheRegionAccessStrategy(regionName);
      if (strategy == null) {
         return null;
      }
      Field delegateField = getField(strategy.getClass(), "delegate");
      Object delegate = delegateField.get(strategy);
      if (delegate == null) {
         return null;
      }
      if (InvalidationCacheAccessDelegate.class.isInstance(delegate)) {
         Field validatorField = InvalidationCacheAccessDelegate.class.getDeclaredField("putValidator");
         validatorField.setAccessible(true);
         return (PutFromLoadValidator) validatorField.get(delegate);
      } else {
         return null;
      }
   }
