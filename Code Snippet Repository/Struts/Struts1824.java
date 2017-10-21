    public void testGetUriParamId() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("article", "123");
        param.put("viewId", "456");
        ActionMapping am = new ActionMapping();
        am.setName("view");
        am.setNamespace("secure");
        am.setParams(param);

        assertEquals("secureview/456", mapper.getUriFromActionMapping(am));
    }
