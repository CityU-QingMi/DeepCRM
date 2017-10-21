    @Test
    public void testCreateFileVisitorTestModeIsActionTestMode() {
        final DeleteAction delete = createAnyFilter("any", true, 0, false);
        assertFalse(delete.isTestMode());
        final FileVisitor<Path> visitor = delete.createFileVisitor(delete.getBasePath(), delete.getPathConditions());
        assertTrue(visitor instanceof DeletingVisitor);
        assertFalse(((DeletingVisitor) visitor).isTestMode());

        final DeleteAction deleteTestMode = createAnyFilter("any", true, 0, true);
        assertTrue(deleteTestMode.isTestMode());
        final FileVisitor<Path> testVisitor = deleteTestMode.createFileVisitor(delete.getBasePath(),
                delete.getPathConditions());
        assertTrue(testVisitor instanceof DeletingVisitor);
        assertTrue(((DeletingVisitor) testVisitor).isTestMode());
    }
