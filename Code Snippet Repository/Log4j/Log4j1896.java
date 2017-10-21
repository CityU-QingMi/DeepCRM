    @Test
    public void testInTestModeFileIsNotDeletedEvenIfAllFiltersAccept() throws IOException {
        final Path base = Paths.get("/a/b/c");
        final FixedCondition ACCEPT_ALL = new FixedCondition(true);
        final List<? extends PathCondition> filters = Arrays.asList(ACCEPT_ALL, ACCEPT_ALL, ACCEPT_ALL);
        final DeletingVisitorHelper visitor = new DeletingVisitorHelper(base, filters, true);

        final Path any = Paths.get("/a/b/c/any");
        visitor.visitFile(any, null);
        assertFalse(visitor.deleted.contains(any));
    }
