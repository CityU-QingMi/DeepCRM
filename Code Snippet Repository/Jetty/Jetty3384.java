    @Test
    public void testRead() throws Exception
    {
        _in.addContent(new TContent("AB"));
        _in.addContent(new TContent("CD"));
        _fillAndParseSimulate.offer("EF");
        _fillAndParseSimulate.offer("GH");
        Assert.assertThat(_in.available(), Matchers.equalTo(2));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));

        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(0L));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));
        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(1L));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'B'));
        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(2L));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'C'));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'D'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded CD"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'E'));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'F'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 2"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded EF"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'G'));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'H'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded GH"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.getContentConsumed(), Matchers.equalTo(8L));

        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
