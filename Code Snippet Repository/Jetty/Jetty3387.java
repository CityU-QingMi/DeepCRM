    @Test
    public void testReadEarlyEOF() throws Exception
    {
        _in.addContent(new TContent("AB"));
        _in.addContent(new TContent("CD"));
        _in.earlyEOF();

        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_in.available(), Matchers.equalTo(2));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'B'));

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'C'));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_in.read(), Matchers.equalTo((int)'D'));

        try
        {
            _in.read();
            Assert.fail();
        }
        catch (EOFException eof)
        {
            Assert.assertThat(_in.isFinished(), Matchers.equalTo(true));
        }

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded CD"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
