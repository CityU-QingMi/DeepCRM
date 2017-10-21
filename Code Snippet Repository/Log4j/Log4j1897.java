    @Test
    public void testVisitFileRelativizesAgainstBase() throws IOException {

        final PathCondition filter = new PathCondition() {

            @Override
            public boolean accept(final Path baseDir, final Path relativePath, final BasicFileAttributes attrs) {
                final Path expected = Paths.get("relative");
                assertEquals(expected, relativePath);
                return true;
            }

            @Override
            public void beforeFileTreeWalk() {
            }
        };
        final Path base = Paths.get("/a/b/c");
        final DeletingVisitorHelper visitor = new DeletingVisitorHelper(base, Arrays.asList(filter), false);

        final Path child = Paths.get("/a/b/c/relative");
        visitor.visitFile(child, null);
    }
