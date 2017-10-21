    @Test
    public void testSubclassedCommandHelp() throws Exception {
        @Command(name = "parent", description = "parent description")
        class ParentOption {
        }
        @Command(name = "child", description = "child description")
        class ChildOption extends ParentOption {
        }
        String actual = usageString(new ChildOption(), Help.Ansi.OFF);
        assertEquals(String.format(
                "Usage: child%n" +
                "child description%n"), actual);
    }
