    @Test
    @Deprecated
    public void testSidConversion() throws Exception
    {
        String sid4 = "S-1-4-21-3623811015-3361044348-30300820";
        String sid5 = "S-1-5-21-3623811015-3361044348-30300820-1013";
        String sid6 = "S-1-6-21-3623811015-3361044348-30300820-1013-23445";
        String sid12 = "S-1-12-21-3623811015-3361044348-30300820-1013-23445-21-3623811015-3361044348-30300820-1013-23445";

        byte[] sid4Bytes = StringUtil.sidStringToBytes(sid4);
        byte[] sid5Bytes = StringUtil.sidStringToBytes(sid5);
        byte[] sid6Bytes = StringUtil.sidStringToBytes(sid6);
        byte[] sid12Bytes = StringUtil.sidStringToBytes(sid12);

        Assert.assertEquals(sid4, StringUtil.sidBytesToString(sid4Bytes));
        Assert.assertEquals(sid5, StringUtil.sidBytesToString(sid5Bytes));
        Assert.assertEquals(sid6, StringUtil.sidBytesToString(sid6Bytes));
        Assert.assertEquals(sid12, StringUtil.sidBytesToString(sid12Bytes));

    }
