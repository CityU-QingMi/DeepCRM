      @CacheEntryActivated
      @CacheEntryCreated
      @CacheEntryInvalidated
      @CacheEntryLoaded
      @CacheEntryModified
      @CacheEntryPassivated
      @CacheEntryRemoved
      @CacheEntryVisited
      public void event(Event event) throws Throwable {
         List<Method> methods = this.methods.get(event.getType());
         if (methods != null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            ClassLoader visible = (ClassLoader) cache.classLoaderRef.get();
            cache.setContextClassLoader(visible);
            try {
               for (Method method : this.methods.get(event.getType())) {
                  try {
                     method.invoke(this.listener, event);
                  }
                  catch (InvocationTargetException e) {
                     throw e.getCause();
                  }
               }
            }
            finally {
               cache.setContextClassLoader(classLoader);
            }
         }
      }
