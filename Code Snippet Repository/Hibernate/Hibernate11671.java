      @Override
      protected void amendCacheConfiguration(String cacheName, ConfigurationBuilder configurationBuilder) {
         super.amendCacheConfiguration(cacheName, configurationBuilder);
         configurationBuilder.transaction().cacheStopTimeout(1, TimeUnit.SECONDS);
         if (INJECT_FAILURES) {
            // failure to write into timestamps would cause failure even though both DB and cache has been updated
            if (!cacheName.equals("timestamps") && !cacheName.endsWith(InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE)) {
               configurationBuilder.customInterceptors().addInterceptor()
                  .interceptorClass(FailureInducingInterceptor.class)
                  .position(InterceptorConfiguration.Position.FIRST);
               log.trace("Injecting FailureInducingInterceptor into " + cacheName);
            } else {
               log.trace("Not injecting into " + cacheName);
            }
         }
      }
