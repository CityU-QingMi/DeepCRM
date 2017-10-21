    @Test
    public void testSecurityElementExample13_3() throws Exception
    {
        HttpConstraintElement httpConstraintElement = new HttpConstraintElement(EmptyRoleSemantic.DENY);
        ServletSecurityElement element = new ServletSecurityElement(httpConstraintElement);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(1, mappings.size());
        ConstraintMapping mapping = mappings.get(0);
        Assert.assertTrue(mapping.getConstraint().isForbidden());
    }
