    @Test
    public void convertHexDigitTest()
    {   
        Assert.assertEquals((byte)0,TypeUtil.convertHexDigit((byte)'0'));
        Assert.assertEquals((byte)9,TypeUtil.convertHexDigit((byte)'9'));
        Assert.assertEquals((byte)10,TypeUtil.convertHexDigit((byte)'a'));
        Assert.assertEquals((byte)10,TypeUtil.convertHexDigit((byte)'A'));
        Assert.assertEquals((byte)15,TypeUtil.convertHexDigit((byte)'f'));
        Assert.assertEquals((byte)15,TypeUtil.convertHexDigit((byte)'F'));
        
        Assert.assertEquals((int)0,TypeUtil.convertHexDigit((int)'0'));
        Assert.assertEquals((int)9,TypeUtil.convertHexDigit((int)'9'));
        Assert.assertEquals((int)10,TypeUtil.convertHexDigit((int)'a'));
        Assert.assertEquals((int)10,TypeUtil.convertHexDigit((int)'A'));
        Assert.assertEquals((int)15,TypeUtil.convertHexDigit((int)'f'));
        Assert.assertEquals((int)15,TypeUtil.convertHexDigit((int)'F'));
    }
