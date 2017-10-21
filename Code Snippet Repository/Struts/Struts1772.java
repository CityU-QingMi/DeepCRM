    public void testGetUriWithOriginalExtension() throws Exception {
        ActionMapping mapping = new ActionMapping("actionName", "/ns", null, new HashMap());

        ActionMapping orig = new ActionMapping();
        orig.setExtension("foo");
        ActionContext.getContext().put(ServletActionContext.ACTION_MAPPING, orig);

        DefaultActionMapper mapper = new DefaultActionMapper();
        assertEquals("/ns/actionName.foo", mapper.getUriFromActionMapping(mapping));
    }
