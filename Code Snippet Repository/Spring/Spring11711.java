	@Test
	public void ambiguousHeaderPreflightRequest() throws Exception {
		this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "header1");
		ResponseEntity<String> entity = performOptions("/ambiguous-header", this.headers, String.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("http://site1.com", entity.getHeaders().getAccessControlAllowOrigin());
		assertArrayEquals(new HttpMethod[] {HttpMethod.GET},
				entity.getHeaders().getAccessControlAllowMethods().toArray());
		assertArrayEquals(new String[] {"header1"},
				entity.getHeaders().getAccessControlAllowHeaders().toArray());
		assertEquals(true, entity.getHeaders().getAccessControlAllowCredentials());
	}
