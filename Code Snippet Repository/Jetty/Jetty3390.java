    @Test
    public void testAsyncRead() throws Exception
    {
        _in.setReadListener(_listener);
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onReadUnready"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        _in.addContent(new TContent("AB"));
        _fillAndParseSimulate.add("CD");

        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onDataAvailable"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
        _in.run();
        Assert.assertThat(_history.poll(), Matchers.equalTo("l.onDataAvailable"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));

        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'B'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 1"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onDataAvailable"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'C'));

        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'D'));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded CD"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onReadUnready"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
