    @Test
    public void testSecurityElementExample13_7() throws Exception
    {
        List<HttpMethodConstraintElement> methodElements = new ArrayList<HttpMethodConstraintElement>();
        methodElements.add(new HttpMethodConstraintElement("TRACE", new HttpConstraintElement(EmptyRoleSemantic.DENY)));
        ServletSecurityElement element = new ServletSecurityElement(new HttpConstraintElement(TransportGuarantee.NONE, "R1"), methodElements);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(2, mappings.size());
        Assert.assertTrue(mappings.get(0).getMethodOmissions() != null);
        Assert.assertEquals("TRACE", mappings.get(0).getMethodOmissions()[0]);
        Assert.assertTrue(mappings.get(0).getConstraint().getAuthenticate());
        Assert.assertEquals("R1", mappings.get(0).getConstraint().getRoles()[0]);
        Assert.assertEquals("TRACE", mappings.get(1).getMethod());
        Assert.assertTrue(mappings.get(1).getMethodOmissions() == null);
        Assert.assertEquals(0, mappings.get(1).getConstraint().getDataConstraint());
        Assert.assertTrue(mappings.get(1).getConstraint().isForbidden());
    }
