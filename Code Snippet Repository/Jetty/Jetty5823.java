    @Test
    public void testResourceContent()
        throws Exception
    {
        assumeThat(data.content, notNullValue());
        
        InputStream in = data.resource.getInputStream();
        String c = IO.toString(in);
        assertThat("Content: " + data.test,c,startsWith(data.content));
    }
