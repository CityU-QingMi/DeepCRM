    private void compareResults (ConstraintMapping[] expectedMappings, List<ConstraintMapping> actualMappings)
    {
        assertNotNull(actualMappings);
        assertEquals(expectedMappings.length, actualMappings.size());

        for (int k=0; k < actualMappings.size(); k++)
        {
            ConstraintMapping am = actualMappings.get(k);
            boolean matched  = false;

            for (int i=0; i< expectedMappings.length && !matched; i++)
            {
                ConstraintMapping em = expectedMappings[i];
                if (em.getPathSpec().equals(am.getPathSpec()))
                {
                    if ((em.getMethod()==null && am.getMethod() == null) || em.getMethod() != null && em.getMethod().equals(am.getMethod()))
                    {
                        matched = true;

                        assertEquals(em.getConstraint().getAuthenticate(), am.getConstraint().getAuthenticate());
                        assertEquals(em.getConstraint().getDataConstraint(), am.getConstraint().getDataConstraint());
                        if (em.getMethodOmissions() == null)
                        {
                            assertNull(am.getMethodOmissions());
                        }
                        else
                        {
                            assertTrue(Arrays.equals(am.getMethodOmissions(), em.getMethodOmissions()));
                        }

                        if (em.getConstraint().getRoles() == null)
                        {
                            assertNull(am.getConstraint().getRoles());
                        }
                        else
                        {
                            assertTrue(Arrays.equals(em.getConstraint().getRoles(), am.getConstraint().getRoles()));
                        }
                    }
                }
            }

            if (!matched)
                fail("No expected ConstraintMapping matching method:"+am.getMethod()+" pathSpec: "+am.getPathSpec());
        }
    }
