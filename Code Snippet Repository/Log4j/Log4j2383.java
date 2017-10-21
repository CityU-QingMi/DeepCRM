    @Test
    public void testDefaultParameterRenderer_rendersSpacePrefixByDefaultForParametersWithPositiveArity() {
        class Required {
            @Parameters(description = "required") String required;
        }
        Help help = new Help(new Required());
        Help.IParameterRenderer renderer = help.createDefaultParameterRenderer();
        Help.IParamLabelRenderer parameterRenderer = Help.createMinimalParamLabelRenderer();
        Field field = help.positionalParametersFields.get(0);
        Text[][] row1 = renderer.render(field.getAnnotation(Parameters.class), field, parameterRenderer, help.colorScheme);
        assertEquals(1, row1.length);
        assertArrayEquals(Arrays.toString(row1[0]), textArray(help, " ", "", "", "required", "required"), row1[0]);
    }
