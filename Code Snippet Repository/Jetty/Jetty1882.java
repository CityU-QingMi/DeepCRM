    @Test
    public void testMethodNameMining() throws Exception
    {
        ObjectMBean mbean = new ObjectMBean(new Derived());

        Assert.assertEquals("fullName",mbean.toVariableName("getFullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("getfullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("isFullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("isfullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("setFullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("setfullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("FullName"));
        Assert.assertEquals("fullName",mbean.toVariableName("fullName"));
    }
