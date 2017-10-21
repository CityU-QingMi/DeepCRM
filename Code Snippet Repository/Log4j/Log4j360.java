    @Test
    public void flowTracingMessage() {
        logger.traceEntry(new JsonMessage(System.getProperties()));
        final Response response = new Response(-1, "Generic error");
        logger.traceExit(new JsonMessage(response),  response);
        assertEquals(2, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("\"java.runtime.name\":"));
        assertThat("incorrect Exit", results.get(1), startsWith("EXIT[ FLOW ] TRACE Exit"));
        assertThat("Missing exit data", results.get(1), containsString("\"message\":\"Generic error\""));
    }
