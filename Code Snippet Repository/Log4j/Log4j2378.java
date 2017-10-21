    @Test
    public void testDefaultOptionRenderer_rendersShortestOptionNameThenOtherOptionNamesAndDescription() {
        @Command(showDefaultValues = true)
        class Example {
            @Option(names = {"---long", "-L"}, description = "long description") String longField;
            @Option(names = {"-b", "-a", "--alpha"}, description = "other") String otherField = "abc";
        }
        Help help = new Help(new Example());
        Help.IOptionRenderer renderer = help.createDefaultOptionRenderer();
        Help.IParamLabelRenderer parameterRenderer = help.createDefaultParamLabelRenderer();
        Field field = help.optionFields.get(0);
        Text[][] row1 = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, help.colorScheme);
        assertEquals(2, row1.length);
        assertArrayEquals(Arrays.toString(row1[0]), textArray(help, "", "-L", ",", "---long=<longField>", "long description"), row1[0]);
        assertArrayEquals(Arrays.toString(row1[1]), textArray(help, "", "", "", "", "  Default: null"), row1[1]);

        field = help.optionFields.get(1);
        Text[][] row2 = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, help.colorScheme);
        assertEquals(2, row2.length);
        assertArrayEquals(Arrays.toString(row2[0]), textArray(help, "", "-b", ",", "-a, --alpha=<otherField>", "other"), row2[0]);
        assertArrayEquals(Arrays.toString(row2[1]), textArray(help, "", "", "", "", "  Default: abc"), row2[1]);
    }
