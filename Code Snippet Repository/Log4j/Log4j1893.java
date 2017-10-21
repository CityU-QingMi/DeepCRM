    @Test
    public void testRejectedFilesAreNotDeleted() throws IOException {
        final Path base = Paths.get("/a/b/c");
        final FixedCondition REJECT_ALL = new FixedCondition(false);
        final DeletingVisitorHelper visitor = new DeletingVisitorHelper(base, Arrays.asList(REJECT_ALL), false);

        final Path any = Paths.get("/a/b/c/any");
        visitor.visitFile(any, null);
        assertFalse(visitor.deleted.contains(any));
    }
