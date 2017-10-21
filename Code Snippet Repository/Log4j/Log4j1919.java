    @Test
    public void testSelectFilesToDelete() {
        final Configuration config = new DefaultConfiguration();
        config.initialize(); // creates the ScriptManager

        final Script script = new Script("test", "javascript", "pathList;"); // script that returns pathList
        final ScriptCondition condition = new ScriptCondition(script, config);
        final List<PathWithAttributes> pathList = new ArrayList<>();
        final Path base = Paths.get("baseDirectory");
        final List<PathWithAttributes> result = condition.selectFilesToDelete(base, pathList);
        assertSame(result, pathList);
    }
