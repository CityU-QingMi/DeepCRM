    @Test
    public void testAllFiltersMustAcceptOrFileIsNotDeleted() throws IOException {
        final Path base = Paths.get("/a/b/c");
        final FixedCondition ACCEPT_ALL = new FixedCondition(true);
        final FixedCondition REJECT_ALL = new FixedCondition(false);
        final List<? extends PathCondition> filters = Arrays.asList(ACCEPT_ALL, ACCEPT_ALL, REJECT_ALL);
        final DeletingVisitorHelper visitor = new DeletingVisitorHelper(base, filters, false);

        final Path any = Paths.get("/a/b/c/any");
        visitor.visitFile(any, null);
        assertFalse(visitor.deleted.contains(any));
    }
