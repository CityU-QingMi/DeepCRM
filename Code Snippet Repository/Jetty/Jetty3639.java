    @Test
    public void testStatusCodesNoErrorHandler() throws Exception
    {
        _server.removeBean(_server.getBean(ErrorHandler.class));
        Response response = getResponse();

        response.sendError(404);
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getReason());

        response = getResponse();

        response.sendError(500, "Database Error");
        assertEquals(500, response.getStatus());
        assertEquals("Database Error", response.getReason());
        assertThat(response.getHeader(HttpHeader.CACHE_CONTROL.asString()),Matchers.nullValue());

        response = getResponse();

        response.setStatus(200);
        assertEquals(200, response.getStatus());
        assertEquals(null, response.getReason());

        response = getResponse();

        response.sendError(406, "Super Nanny");
        assertEquals(406, response.getStatus());
        assertEquals("Super Nanny", response.getReason());
        assertThat(response.getHeader(HttpHeader.CACHE_CONTROL.asString()),Matchers.nullValue());
    }
