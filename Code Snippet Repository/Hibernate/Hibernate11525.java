   public CollectionRegionImpl getCollectionRegion(String name, CacheDataDescription cacheDataDescription) {
      if (collectionRegionMap == null) {
         collectionRegionMap = new HashMap<String, CollectionRegionImpl>();
         return buildAndStoreCollectionRegion(name, cacheDataDescription);
      }
      CollectionRegionImpl region = collectionRegionMap.get(name);
      if (region == null) {
         region = buildAndStoreCollectionRegion(name, cacheDataDescription);
         collectionRegionMap.put(name, region);
      }
      return region;
   }
