    @Test
    public void testAcceptedFilesAreDeleted() throws IOException {
        final Path base = Paths.get("/a/b/c");
        final FixedCondition ACCEPT_ALL = new FixedCondition(true);
        final DeletingVisitorHelper visitor = new DeletingVisitorHelper(base, Arrays.asList(ACCEPT_ALL), false);

        final Path any = Paths.get("/a/b/c/any");
        visitor.visitFile(any, null);
        assertTrue(visitor.deleted.contains(any));
    }
