	@Test
	public void ambiguousProducesPreflightRequest() throws Exception {
		this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		ResponseEntity<String> entity = performOptions("/ambiguous-produces", this.headers, String.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("http://site1.com", entity.getHeaders().getAccessControlAllowOrigin());
		assertArrayEquals(new HttpMethod[] {HttpMethod.GET},
				entity.getHeaders().getAccessControlAllowMethods().toArray());
		assertEquals(true, entity.getHeaders().getAccessControlAllowCredentials());
	}
