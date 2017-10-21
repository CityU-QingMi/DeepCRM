    @Test
    public void testDefaultOptionRenderer_rendersSpecifiedMarkerForRequiredOptionsWithDefault() {
        @Command(requiredOptionMarker = '*', showDefaultValues = true)
        class Example {
            @Option(names = {"-b", "-a", "--alpha"}, required = true, description = "other") String otherField ="abc";
        }
        Help help = new Help(new Example());
        Help.IOptionRenderer renderer = help.createDefaultOptionRenderer();
        Help.IParamLabelRenderer parameterRenderer = help.createDefaultParamLabelRenderer();
        Field field = help.optionFields.get(0);
        Text[][] row = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, help.colorScheme);
        assertEquals(2, row.length);
        assertArrayEquals(Arrays.toString(row[0]), textArray(help, "*", "-b", ",", "-a, --alpha=<otherField>", "other"), row[0]);
        assertArrayEquals(Arrays.toString(row[1]), textArray(help, "", "", "", "", "  Default: abc"), row[1]);
    }
