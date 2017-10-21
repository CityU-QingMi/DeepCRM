   public EntityRegionImpl getEntityRegion(String name, CacheDataDescription cacheDataDescription) {
      if (entityRegionMap == null) {
         entityRegionMap = new HashMap<String, EntityRegionImpl>();
         return buildAndStoreEntityRegion(name, cacheDataDescription);
      }
      EntityRegionImpl region = entityRegionMap.get(name);
      if (region == null) {
         region = buildAndStoreEntityRegion(name, cacheDataDescription);
      }
      return region;
   }
