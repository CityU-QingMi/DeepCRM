    @Test
    public void testToShortString() 
    {
        assertToShortString("1.8","1.8");
        assertToShortString("1.8.0","1.8.0");
        assertToShortString("1.8.0_3","1.8.0_3");
        assertToShortString("1.8.0_03","1.8.0_03");
        assertToShortString("1.8.0_45","1.8.0_45");
        assertToShortString("1.8.0_45-internal","1.8.0_45");
        assertToShortString("1.8.0-debug","1.8.0");
    }
