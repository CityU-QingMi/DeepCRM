    @Test
    public void testExpandLoop()
    {
        Props props = new Props();
        props.setProperty("aa","${bb}",FROM_TEST);
        props.setProperty("bb","${cc}",FROM_TEST);
        props.setProperty("cc","${aa}",FROM_TEST);

        try
        {
            // Should throw exception
            props.expand("val=${aa}");
            fail("Should have thrown a " + PropsException.class);
        }
        catch (PropsException e)
        {
            assertThat(e.getMessage(),is("Property expansion loop detected: aa -> bb -> cc -> aa"));
        }
    }
