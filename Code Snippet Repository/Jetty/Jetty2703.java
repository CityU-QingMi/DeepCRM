    @Test
    public void testSecurityElementExample13_5() throws Exception
    {
        List<HttpMethodConstraintElement> methodElements = new ArrayList<HttpMethodConstraintElement>();
        methodElements.add(new HttpMethodConstraintElement("GET", new HttpConstraintElement(TransportGuarantee.NONE, "R1")));
        methodElements.add(new HttpMethodConstraintElement("POST", new HttpConstraintElement(TransportGuarantee.CONFIDENTIAL, "R1")));
        ServletSecurityElement element = new ServletSecurityElement(methodElements);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(2, mappings.size());
        Assert.assertEquals("GET", mappings.get(0).getMethod());
        Assert.assertEquals("R1", mappings.get(0).getConstraint().getRoles()[0]);
        Assert.assertTrue(mappings.get(0).getMethodOmissions() == null);
        Assert.assertEquals(0, mappings.get(0).getConstraint().getDataConstraint());
        Assert.assertEquals("POST", mappings.get(1).getMethod());
        Assert.assertEquals("R1", mappings.get(1).getConstraint().getRoles()[0]);
        Assert.assertEquals(2, mappings.get(1).getConstraint().getDataConstraint());
        Assert.assertTrue(mappings.get(1).getMethodOmissions() == null);
    }
