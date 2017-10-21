   @Test
   public void testEntityLifecycle() throws InterruptedException {
      if (!PROFILE) {
         System.out.printf("[provider=%s] Warming up\n", provider);
         doEntityLifecycle(true);

         // Recreate session factory cleaning everything
         afterClass();
         beforeClass();
      }

      System.out.printf("[provider=%s] Testing...\n", provider);
      doEntityLifecycle(false);
   }
