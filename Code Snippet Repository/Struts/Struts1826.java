    public void testGetMapping3() throws Exception {
        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/myapp/view/12/region/europe");

        ActionMapping am = mapper.getMapping(request, null);
        assertEquals("myapp", am.getName());
        assertEquals(2, am.getParams().size());
        assertEquals("12", am.getParams().get("view"));
        assertEquals("europe", am.getParams().get("region"));
    }
