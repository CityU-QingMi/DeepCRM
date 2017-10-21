    public void testAcceptParams() throws Exception {
        // given
        initDispatcherWithConfigs("struts-default.xml");
        StrutsResultFactory builder = (StrutsResultFactory) container.getInstance(ResultFactory.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("accept", "ok");
        params.put("reject", "bad");
        ResultConfig config = new ResultConfig.Builder("struts", MyResult.class.getName()).addParams(params).build();
        Map<String, Object> context = new HashMap<String, Object>();

        // when
        Result result = builder.buildResult(config, context);

        // then
        assertEquals("ok", ((MyResult)result).getAccept());
        assertEquals("ok", ((MyResult)result).getReject());
    }
