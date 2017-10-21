    @Test
    @TestForIssue( jiraKey = "" )
    public void test() throws Exception {
        Enhancer enhancer = new Enhancer( new DefaultEnhancementContext() );
        try {
            String resourceName = Hidden.class.getName().replace( '.', '/' ) + ".class";
            URL url = getClass().getClassLoader().getResource( resourceName );
            if ( url != null ) {
                Files.delete( Paths.get( url.toURI() ) );
                enhancer.loadCtClassFromClass( Hidden.class );
            }
            fail( "Should throw FileNotFoundException!" );
        } catch ( Exception expected ) {
            assertSame( FileNotFoundException.class, expected.getCause().getClass() );
        }
    }
