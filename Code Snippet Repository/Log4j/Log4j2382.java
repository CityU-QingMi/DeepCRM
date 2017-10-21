    @Test
    public void testDefaultOptionRenderer_rendersSpacePrefixByDefaultForRequiredOptionsWithDefaultValue() {
        //@Command(showDefaultValues = true) // set programmatically
        class Example {
            @Option(names = {"-b", "-a", "--alpha"}, required = true, description = "other") String otherField;
        }
        Help help = new Help(new Example());
        help.showDefaultValues = true;
        Help.IOptionRenderer renderer = help.createDefaultOptionRenderer();
        Help.IParamLabelRenderer parameterRenderer = help.createDefaultParamLabelRenderer();
        Field field = help.optionFields.get(0);
        Text[][] row = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, help.colorScheme);
        assertEquals(2, row.length);
        assertArrayEquals(Arrays.toString(row[0]), textArray(help, " ", "-b", ",", "-a, --alpha=<otherField>", "other"), row[0]);
        assertArrayEquals(Arrays.toString(row[1]), textArray(help, "",    "", "",  "", "  Default: null"), row[1]);
    }
