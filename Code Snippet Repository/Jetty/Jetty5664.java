    @Test
    public void testGetLocationOfClass() throws Exception
    {
        String mavenRepoPathProperty = System.getProperty( "mavenRepoPath");
        Assume.assumeNotNull(mavenRepoPathProperty);
        Path mavenRepoPath = Paths.get( mavenRepoPathProperty );

        String mavenRepo = mavenRepoPath.toFile().getPath().replaceAll("\\\\", "/");

        // Classes from maven dependencies
        assertThat(TypeUtil.getLocationOfClass(Assert.class).toASCIIString(),containsString(mavenRepo));
        
        // Class from project dependencies
        assertThat(TypeUtil.getLocationOfClass(TypeUtil.class).toASCIIString(),containsString("/classes/"));
        
        // Class from JVM core
        String expectedJavaBase = "/rt.jar";
        if(JDK.IS_9)
            expectedJavaBase = "/java.base/";
            
        assertThat(TypeUtil.getLocationOfClass(String.class).toASCIIString(),containsString(expectedJavaBase));
    }
