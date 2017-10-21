    @Test
    public void testAcceptIgnoresBasePathAndAttributes() {
        final IfFileName pathFilter = IfFileName.createNameCondition("path", null);
        final Path relativePath = Paths.get("path");
        assertTrue(pathFilter.accept(null, relativePath, null));
        
        final IfFileName regexFilter = IfFileName.createNameCondition(null, "regex");
        final Path pathMatchingRegex = Paths.get("regex");
        assertTrue(regexFilter.accept(null, pathMatchingRegex, null));
    }
