    @Test
    public void testMinimalOptionRenderer_rendersFirstDeclaredOptionNameAndDescription() {
        class Example {
            @Option(names = {"---long", "-L"}, description = "long description") String longField;
            @Option(names = {"-b", "-a", "--alpha"}, description = "other") String otherField;
        }
        Help.IOptionRenderer renderer = Help.createMinimalOptionRenderer();
        Help help = new Help(new Example(), Help.defaultColorScheme(Help.Ansi.ON));
        Help.IParamLabelRenderer parameterRenderer = help.createDefaultParamLabelRenderer();
        Field field = help.optionFields.get(0);
        Text[][] row1 = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, Help.defaultColorScheme(
                help.ansi()));
        assertEquals(1, row1.length);
        //assertArrayEquals(new String[]{"---long=<longField>", "long description"}, row1[0]);
        assertArrayEquals(new Text[]{
                help.ansi().new Text(format("%s---long%s=%s<longField>%s", "@|fg(yellow) ", "|@", "@|italic ", "|@")),
                help.ansi().new Text("long description")}, row1[0]);

        field = help.optionFields.get(1);
        Text[][] row2 = renderer.render(field.getAnnotation(Option.class), field, parameterRenderer, Help.defaultColorScheme(
                help.ansi()));
        assertEquals(1, row2.length);
        //assertArrayEquals(new String[]{"-b=<otherField>", "other"}, row2[0]);
        assertArrayEquals(new Text[]{
                help.ansi().new Text(format("%s-b%s=%s<otherField>%s", "@|fg(yellow) ", "|@", "@|italic ", "|@")),
                help.ansi().new Text("other")}, row2[0]);
    }
