    @Test
    public void testConstraints() throws Exception
    {
        List<ConstraintMapping> mappings = new ArrayList<>(_security.getConstraintMappings());

        Assert.assertTrue(mappings.get(0).getConstraint().isForbidden());
        Assert.assertFalse(mappings.get(1).getConstraint().isForbidden());
        Assert.assertFalse(mappings.get(2).getConstraint().isForbidden());
        Assert.assertFalse(mappings.get(3).getConstraint().isForbidden());

        Assert.assertFalse(mappings.get(0).getConstraint().isAnyRole());
        Assert.assertTrue(mappings.get(1).getConstraint().isAnyRole());
        Assert.assertFalse(mappings.get(2).getConstraint().isAnyRole());
        Assert.assertFalse(mappings.get(3).getConstraint().isAnyRole());

        Assert.assertFalse(mappings.get(0).getConstraint().hasRole("administrator"));
        Assert.assertTrue(mappings.get(1).getConstraint().hasRole("administrator"));
        Assert.assertTrue(mappings.get(2).getConstraint().hasRole("administrator"));
        Assert.assertFalse(mappings.get(3).getConstraint().hasRole("administrator"));

        Assert.assertTrue(mappings.get(0).getConstraint().getAuthenticate());
        Assert.assertTrue(mappings.get(1).getConstraint().getAuthenticate());
        Assert.assertTrue(mappings.get(2).getConstraint().getAuthenticate());
        Assert.assertFalse(mappings.get(3).getConstraint().getAuthenticate());
    }
