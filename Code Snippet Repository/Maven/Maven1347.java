    @Test
    public void testCalculateDegreeOfConcurrencyWithCoreMultiplier()
    {
        int cores = Runtime.getRuntime().availableProcessors();
        // -T2.2C
        assertEquals( (int) ( cores * 2.2 ), cli.calculateDegreeOfConcurrencyWithCoreMultiplier( "C2.2" ) );
        // -TC2.2
        assertEquals( (int) ( cores * 2.2 ), cli.calculateDegreeOfConcurrencyWithCoreMultiplier( "2.2C" ) );

        try
        {
            cli.calculateDegreeOfConcurrencyWithCoreMultiplier( "CXXX" );
            fail( "Should have failed with a NumberFormatException" );
        }
        catch ( NumberFormatException e )
        {
            // carry on
        }
    }
