    @Test
    public void testDefaultParameterRenderer_appliesToPositionalArgumentsIgnoresSeparator() {
        class WithLabel    { @Parameters(paramLabel = "POSITIONAL_ARGS") String positional; }
        class WithoutLabel { @Parameters()                               String positional; }

        Help withLabel = new Help(new WithLabel());
        Help.IParamLabelRenderer equals = withLabel.createDefaultParamLabelRenderer();
        withLabel.separator = "=";
        Help.IParamLabelRenderer spaced = withLabel.createDefaultParamLabelRenderer();

        Text withSpace = spaced.renderParameterLabel(withLabel.positionalParametersFields.get(0), withLabel.ansi(), Collections.<IStyle>emptyList());
        assertEquals(withSpace.toString(), "POSITIONAL_ARGS", withSpace.toString());
        Text withEquals = equals.renderParameterLabel(withLabel.positionalParametersFields.get(0), withLabel.ansi(), Collections.<IStyle>emptyList());
        assertEquals(withEquals.toString(), "POSITIONAL_ARGS", withEquals.toString());

        Help withoutLabel = new Help(new WithoutLabel());
        withSpace = spaced.renderParameterLabel(withoutLabel.positionalParametersFields.get(0), withoutLabel.ansi(), Collections.<IStyle>emptyList());
        assertEquals(withSpace.toString(), "<positional>", withSpace.toString());
        withEquals = equals.renderParameterLabel(withoutLabel.positionalParametersFields.get(0), withoutLabel.ansi(), Collections.<IStyle>emptyList());
        assertEquals(withEquals.toString(), "<positional>", withEquals.toString());
    }
