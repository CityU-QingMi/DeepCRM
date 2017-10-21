   public static ExpectingInterceptor get(AdvancedCache cache) {
      Optional<ExpectingInterceptor> self = cache.getInterceptorChain().stream().filter(ExpectingInterceptor.class::isInstance).findFirst();
      if (self.isPresent()) {
         return self.get();
      }
      ExpectingInterceptor ei = new ExpectingInterceptor();
      // We are adding this after ICI because we want to handle silent failures, too
      cache.addInterceptorAfter(ei, InvocationContextInterceptor.class);
      return ei;
   }
