    @Test
    public void testSLOTH() throws Exception
    {
        cf.setKeyStorePassword("storepwd");
        cf.setKeyManagerPassword("keypwd");

        cf.start();
        
        cf.dump(System.out, "");
    }
