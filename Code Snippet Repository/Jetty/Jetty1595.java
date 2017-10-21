    private void assertDetectedVars(UriTemplatePathSpec spec, String... expectedVars)
    {
        String prefix = String.format("Spec(\"%s\")",spec.getDeclaration());
        assertEquals(prefix + ".variableCount",expectedVars.length,spec.getVariableCount());
        assertEquals(prefix + ".variable.length",expectedVars.length,spec.getVariables().length);
        for (int i = 0; i < expectedVars.length; i++)
        {
            assertEquals(String.format("%s.variable[%d]",prefix,i),expectedVars[i],spec.getVariables()[i]);
        }
    }
