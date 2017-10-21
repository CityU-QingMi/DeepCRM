    @Test
    public void testStatusCodes() throws Exception
    {
        Response response = getResponse();

        response.sendError(404);
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getReason());

        response = getResponse();

        response.sendError(500, "Database Error");
        assertEquals(500, response.getStatus());
        assertEquals("Database Error", response.getReason());
        assertEquals("must-revalidate,no-cache,no-store", response.getHeader(HttpHeader.CACHE_CONTROL.asString()));

        response = getResponse();

        response.setStatus(200);
        assertEquals(200, response.getStatus());
        assertEquals(null, response.getReason());

        response = getResponse();

        response.sendError(406, "Super Nanny");
        assertEquals(406, response.getStatus());
        assertEquals("Super Nanny", response.getReason());
        assertEquals("must-revalidate,no-cache,no-store", response.getHeader(HttpHeader.CACHE_CONTROL.asString()));
    }
