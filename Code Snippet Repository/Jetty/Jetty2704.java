    @Test
    public void testSecurityElementExample13_6 () throws Exception
    {
        List<HttpMethodConstraintElement> methodElements = new ArrayList<HttpMethodConstraintElement>();
        methodElements.add(new HttpMethodConstraintElement("GET"));
        ServletSecurityElement element = new ServletSecurityElement(new HttpConstraintElement(TransportGuarantee.NONE, "R1"), methodElements);
        List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("foo", "/foo/*", element);
        Assert.assertTrue(!mappings.isEmpty());
        Assert.assertEquals(2, mappings.size());
        Assert.assertTrue(mappings.get(0).getMethodOmissions() != null);
        Assert.assertEquals("GET", mappings.get(0).getMethodOmissions()[0]);
        Assert.assertTrue(mappings.get(0).getConstraint().getAuthenticate());
        Assert.assertEquals("R1", mappings.get(0).getConstraint().getRoles()[0]);
        Assert.assertEquals("GET", mappings.get(1).getMethod());
        Assert.assertTrue(mappings.get(1).getMethodOmissions() == null);
        Assert.assertEquals(0, mappings.get(1).getConstraint().getDataConstraint());
        Assert.assertFalse(mappings.get(1).getConstraint().getAuthenticate());
    }
