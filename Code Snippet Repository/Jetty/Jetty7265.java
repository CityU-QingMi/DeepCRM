    @Test
    public void testMeaningfullSetException() throws Exception
    {
        XmlConfiguration configuration =
            new XmlConfiguration("<Configure class=\"org.eclipse.jetty.xml.TestConfiguration\"><Set name=\"PropertyTest\"><Property name=\"null\"/></Set></Configure>");
        TestConfiguration tc = new TestConfiguration();
        try
        {
            configuration.configure(tc);
            Assert.fail();
        }
        catch(NoSuchMethodException e)
        {
            assertThat(e.getMessage(),containsString("Found setters for int"));
        }
    }
