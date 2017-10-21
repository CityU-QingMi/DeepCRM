    @Test
    public void testInitParams() throws Exception
    {
        ServletHolder holder = new ServletHolder(Source.JAVAX_API);
        ServletRegistration reg = holder.getRegistration();
        try
        {
            reg.setInitParameter(null, "foo");
            fail("null name accepted");
        }
        catch (IllegalArgumentException e)
        {
        }
        try
        {
            reg.setInitParameter("foo", null);
            fail("null value accepted");
        }
        catch (IllegalArgumentException e)
        {
        }
        reg.setInitParameter("foo", "bar");
        assertFalse(reg.setInitParameter("foo", "foo"));

        Set<String> clash = reg.setInitParameters(Collections.singletonMap("foo", "bax"));
        assertTrue("should be one clash", clash != null && clash.size() == 1);

        try
        {
            reg.setInitParameters(Collections.singletonMap((String) null, "bax"));
            fail("null name in map accepted");
        }
        catch (IllegalArgumentException e)
        {
        }
        try
        {
            reg.setInitParameters(Collections.singletonMap("foo", (String) null));
            fail("null value in map accepted");
        }
        catch (IllegalArgumentException e)
        {
        }

        Set<String> clash2 = reg.setInitParameters(Collections.singletonMap("FOO", "bax"));
        assertTrue("should be no clash", clash2.isEmpty());
        assertEquals("setInitParameters should not replace existing non-clashing init parameters", 2, reg.getInitParameters().size());

    }
