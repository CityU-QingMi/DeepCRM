    public void testFullDefaultInterceptorRef() {
        PackageConfig cfg1 = new PackageConfig.Builder("pkg1")
                .defaultInterceptorRef("ref1").build();
        PackageConfig cfg2 = new PackageConfig.Builder("pkg2").defaultInterceptorRef("ref2").build();
        PackageConfig cfg = new PackageConfig.Builder("pkg")
                .addParent(cfg1)
                .addParent(cfg2)
                .build();
        
        assertEquals("ref2", cfg.getFullDefaultInterceptorRef());
    }
