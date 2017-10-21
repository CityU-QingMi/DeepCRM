    public void testGetMapping2() throws Exception {
        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/myapp/12/region/europe");

        ActionMapping am = mapper.getMapping(request, null);
        assertEquals("myapp", am.getName());
        assertEquals(2, am.getParams().size());
        assertEquals("12", am.getParams().get("myappId"));
        assertEquals("europe", am.getParams().get("region"));
    }
