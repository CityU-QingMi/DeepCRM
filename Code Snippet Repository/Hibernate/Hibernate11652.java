      @Parameterized.Parameters(name = "")
      public List<Object[]> getParameters() {
         return Arrays.<Object[]>asList(
               new Object[] { "transactional, invalidation", CacheMode.INVALIDATION_SYNC, AccessType.TRANSACTIONAL },
               new Object[] { "read-only, invalidation", CacheMode.INVALIDATION_SYNC, AccessType.READ_ONLY }, // maybe not needed
               new Object[] { "read-write, invalidation", CacheMode.INVALIDATION_SYNC, AccessType.READ_WRITE },
               new Object[] { "read-write, replicated", CacheMode.REPL_SYNC, AccessType.READ_WRITE },
               new Object[] { "read-write, distributed", CacheMode.DIST_SYNC, AccessType.READ_WRITE },
               new Object[] { "non-strict, replicated", CacheMode.REPL_SYNC, AccessType.NONSTRICT_READ_WRITE }
         );
      }
