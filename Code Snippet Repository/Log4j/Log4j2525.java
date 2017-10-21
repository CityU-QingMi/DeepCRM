    @Test
    public void testSubclassedOptionsWithShadowedOptionNameThrowsDuplicateOptionAnnotationsException() {
        class ParentOption {
            @CommandLine.Option(names = "-p") String path;
        }
        class ChildOption extends ParentOption {
            @CommandLine.Option(names = "-p") String text;
        }
        try {
            CommandLine.populateCommand(new ChildOption(), "");
            fail("expected CommandLine$DuplicateOptionAnnotationsException");
        } catch (DuplicateOptionAnnotationsException ex) {
            String expected = String.format("Option name '-p' is used by both %s.path and %s.text",
                    ParentOption.class.getName(), ChildOption.class.getName());
            assertEquals(expected, ex.getMessage());
        }
    }
