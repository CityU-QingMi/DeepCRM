	@Test
	public void classLevel() throws Exception {
		ResponseEntity<String> entity = performGet("/foo", this.headers, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("*", entity.getHeaders().getAccessControlAllowOrigin());
		assertEquals(false, entity.getHeaders().getAccessControlAllowCredentials());
		assertEquals("foo", entity.getBody());

		entity = performGet("/bar", this.headers, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("*", entity.getHeaders().getAccessControlAllowOrigin());
		assertEquals(false, entity.getHeaders().getAccessControlAllowCredentials());
		assertEquals("bar", entity.getBody());

		entity = performGet("/baz", this.headers, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("http://site1.com", entity.getHeaders().getAccessControlAllowOrigin());
		assertEquals(true, entity.getHeaders().getAccessControlAllowCredentials());
		assertEquals("baz", entity.getBody());
	}
