    @Test
    public void testSecurityElementExample13_2() throws Exception
    {
        HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.CONFIDENTIAL);
        ServletSecurityElement element = new ServletSecurityElement(httpConstraintElement);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(1, mappings.size());
        ConstraintMapping mapping = mappings.get(0);
        Assert.assertEquals(2, mapping.getConstraint().getDataConstraint());
    }
