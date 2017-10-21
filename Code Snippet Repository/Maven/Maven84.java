    @Test
    public void testGetInputStream()
        throws Exception
    {
        StringSource source = new StringSource( "Hello World!" );

        try ( InputStream is = source.getInputStream();
              Scanner scanner = new Scanner( is ) )
        {
            assertEquals( "Hello World!", scanner.nextLine() );
        }
    }
