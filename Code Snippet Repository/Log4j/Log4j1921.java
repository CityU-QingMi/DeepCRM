    @Test
    @Category(Scripts.Groovy.class)
    public void testSelectFilesToDelete3() {
        final Configuration config = new DefaultConfiguration();
        config.initialize(); // creates the ScriptManager

        final List<PathWithAttributes> pathList = new ArrayList<>();
        pathList.add(new PathWithAttributes(Paths.get("/path/1/abc/a.txt"), new DummyFileAttributes()));
        pathList.add(new PathWithAttributes(Paths.get("/path/2/abc/bbb.txt"), new DummyFileAttributes()));
        pathList.add(new PathWithAttributes(Paths.get("/path/3/abc/c.txt"), new DummyFileAttributes()));

        final String scriptText = "" //
                + "import java.nio.file.*;" //
                + "def pattern = ~/(\\d*)[\\/\\\\]abc[\\/\\\\].*\\.txt/;" //
                + "assert pattern.getClass() == java.util.regex.Pattern;" //
                + "def copy = pathList.collect{it};"
                + "pathList.each { pathWithAttribs -> \n" //
                + "  def relative = basePath.relativize pathWithAttribs.path;" //
                + "  println 'relative path: ' + relative;" //
                + "  def str = relative.toString();"
                + "  def m = pattern.matcher(str);" //
                + "  if (m.find()) {" //
                + "    def index = m.group(1) as int;" //
                + "    println 'extracted index: ' + index;" //
                + "    def isOdd = (index % 2) == 1;"
                + "    println 'is odd: ' + isOdd;" //
                + "    if (isOdd) { copy.remove pathWithAttribs}"
                + "  }" //
                + "}" //
                + "println copy;"
                + "copy;";
        final Script script = new Script("test", "groovy", scriptText);
        final ScriptCondition condition = new ScriptCondition(script, config);
        final Path base = Paths.get("/path");
        final List<PathWithAttributes> result = condition.selectFilesToDelete(base, pathList);
        assertEquals(1, result.size());
        assertEquals(Paths.get("/path/2/abc/bbb.txt"), result.get(0).getPath());
    }
