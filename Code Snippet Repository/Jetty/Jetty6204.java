    @Test
    public void testMatchDateDecoder()
    {
        DecoderMetadata metadata = new DecoderMetadata(DateDecoder.class,Date.class,MessageType.TEXT,false);
        JsrParamIdDecoder paramId = new JsrParamIdDecoder(metadata);

        JsrCallable callable = getOnMessageCallableFrom(DateTextSocket.class,"onMessage");
        Param param = new Param(0,Date.class,null);

        Assert.assertThat("Match for Decoder",paramId.process(param,callable),is(true));
    }
