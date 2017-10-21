    @Test
    public void catching() {
        try {
            throw new NullPointerException();
        } catch (final Exception e) {
            logger.catching(e);
            assertEquals(1, results.size());
            assertThat("Incorrect Catching",
                    results.get(0), startsWith("CATCHING[ EXCEPTION ] ERROR Catching java.lang.NullPointerException"));
        }
    }
