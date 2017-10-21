    public void testGetUriParam() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("article", "123");
        ActionMapping am = new ActionMapping();
        am.setName("view");
        am.setNamespace("secure");
        am.setParams(param);

        assertEquals("secureview", mapper.getUriFromActionMapping(am));
    }
