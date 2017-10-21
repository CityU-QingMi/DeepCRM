    @Before
    public void initMappings()
    {
        fh1.setName("fh1");
        fm1.setPathSpec("/*");
        fm1.setFilterHolder(fh1);

        fh2.setName("fh2");
        fm2.setPathSpec("/*");
        fm2.setFilterHolder(fh2);
        
        fh3.setName("fh3");
        fm3.setPathSpec("/*");
        fm3.setFilterHolder(fh3);

        fh4.setName("fh4");
        fm4.setPathSpec("/*");
        fm4.setFilterHolder(fh4);
        
        fh5.setName("fh5");
        fm5.setPathSpec("/*");
        fm5.setFilterHolder(fh5);
        
        sh1.setName("s1");
        sm1.setDefault(false);
        sm1.setPathSpec("/foo/*");
        sm1.setServletName("s1");
        
        sh2.setName("s2");
        sm2.setDefault(false);
        sm2.setPathSpec("/foo/*");
        sm2.setServletName("s2");
        
        sh3.setName("s3");
        sm3.setDefault(true);
        sm3.setPathSpec("/foo/*");
        sm3.setServletName("s3");
        
        
    }
