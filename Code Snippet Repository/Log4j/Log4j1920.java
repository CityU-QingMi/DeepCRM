    @Test
    public void testSelectFilesToDelete2() {
        final Configuration config = new DefaultConfiguration();
        config.initialize(); // creates the ScriptManager

        final List<PathWithAttributes> pathList = new ArrayList<>();
        pathList.add(new PathWithAttributes(Paths.get("/path/1"), new DummyFileAttributes()));
        pathList.add(new PathWithAttributes(Paths.get("/path/2"), new DummyFileAttributes()));
        pathList.add(new PathWithAttributes(Paths.get("/path/3"), new DummyFileAttributes()));

        final String scriptText = "pathList.remove(1);" //
                + "pathList;";
        final Script script = new Script("test", "javascript", scriptText);
        final ScriptCondition condition = new ScriptCondition(script, config);
        final Path base = Paths.get("baseDirectory");
        final List<PathWithAttributes> result = condition.selectFilesToDelete(base, pathList);
        assertSame(result, pathList);
        assertEquals(2, result.size());
        assertEquals(Paths.get("/path/1"), result.get(0).getPath());
        assertEquals(Paths.get("/path/3"), result.get(1).getPath());
    }
