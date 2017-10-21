    @Test
    public void testSecurityElementExample13_4() throws Exception
    {
        HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE, "R1");
        ServletSecurityElement element = new ServletSecurityElement(httpConstraintElement);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(1, mappings.size());
        ConstraintMapping mapping = mappings.get(0);
        Assert.assertTrue(mapping.getConstraint().getAuthenticate());
        Assert.assertTrue(mapping.getConstraint().getRoles() != null);
        Assert.assertEquals(1, mapping.getConstraint().getRoles().length);
        Assert.assertEquals("R1", mapping.getConstraint().getRoles()[0]);
        Assert.assertEquals(0, mapping.getConstraint().getDataConstraint());
    }
