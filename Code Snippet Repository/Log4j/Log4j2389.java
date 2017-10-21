    @Test
    public void testDefaultParameterRenderer_showsParamLabelIfPresentOrFieldNameOtherwise() {
        class Example {
            @Option(names = "--without" ) String longField;
            @Option(names = "--with", paramLabel = "LABEL") String otherField;
        }
        Help help = new Help(new Example());
        Help.IParamLabelRenderer equalSeparatedParameterRenderer = help.createDefaultParamLabelRenderer();
        help.separator = " ";
        Help.IParamLabelRenderer spaceSeparatedParameterRenderer = help.createDefaultParamLabelRenderer();

        String[] expected = new String[] {
                "<longField>",
                "LABEL",
        };
        int i = -1;
        for (Field field : help.optionFields) {
            i++;
            Text withSpace = spaceSeparatedParameterRenderer.renderParameterLabel(field, help.ansi(), Collections.<IStyle>emptyList());
            assertEquals(withSpace.toString(), " " + expected[i], withSpace.toString());
            Text withEquals = equalSeparatedParameterRenderer.renderParameterLabel(field, help.ansi(), Collections.<IStyle>emptyList());
            assertEquals(withEquals.toString(), "=" + expected[i], withEquals.toString());
        }
    }
