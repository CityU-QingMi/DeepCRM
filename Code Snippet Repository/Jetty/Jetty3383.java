    @Test
    public void testConsumeAllEOF() throws Exception
    {
        _in.addContent(new TContent("AB"));
        _in.addContent(new TContent("CD"));
        _fillAndParseSimulate.offer("EF");
        _fillAndParseSimulate.offer("GH");
        _fillAndParseSimulate.offer("_EOF_");
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));

        Assert.assertTrue(_in.consumeAll());
        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(8L));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded CD"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 3"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded EF"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded GH"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
