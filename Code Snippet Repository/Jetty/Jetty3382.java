    @Test
    public void testConsumeAll() throws Exception
    {
        _in.addContent(new TContent("AB"));
        _in.addContent(new TContent("CD"));
        _fillAndParseSimulate.offer("EF");
        _fillAndParseSimulate.offer("GH");
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));

        Assert.assertFalse(_in.consumeAll());
        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(8L));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded CD"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 2"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded EF"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded GH"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
