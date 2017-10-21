    @Test
    public void testGetInputStream()
        throws Exception
    {
        File txtFile = new File( "target/test-classes/source.txt" );
        FileSource source = new FileSource( txtFile );

        try ( InputStream is = source.getInputStream();
              Scanner scanner = new Scanner( is ) )
        {

            assertEquals( "Hello World!", scanner.nextLine() );
        }
    }
