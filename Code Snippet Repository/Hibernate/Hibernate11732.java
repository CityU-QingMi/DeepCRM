      @Override
      protected Object handleDefault(InvocationContext ctx, VisitableCommand command) throws Throwable {
         ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
         ClassLoaderAwareCache.this.setContextClassLoader(ClassLoaderAwareCache.this.classLoaderRef.get());
         try {
            return super.handleDefault(ctx, command);
         }
         finally {
            ClassLoaderAwareCache.this.setContextClassLoader(classLoader);
         }
      }
