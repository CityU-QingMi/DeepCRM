    @Test
    public void testGetInputStream()
        throws Exception
    {
        URL txtFile = new File( "target/test-classes/source.txt" ).toURI().toURL();
        UrlSource source = new UrlSource( txtFile );
        try ( InputStream is = source.getInputStream();
              Scanner scanner = new Scanner( is ) )
        {
            assertEquals( "Hello World!", scanner.nextLine() );
        }
    }
