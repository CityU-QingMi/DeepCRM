	@Test
	public void preflightRequestWithCustomizedAnnotation() throws Exception {
		this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "header1, header2");
		ResponseEntity<String> entity = performOptions("/customized", this.headers, String.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("http://site1.com", entity.getHeaders().getAccessControlAllowOrigin());
		assertArrayEquals(new HttpMethod[] {HttpMethod.GET},
				entity.getHeaders().getAccessControlAllowMethods().toArray());
		assertArrayEquals(new String[] {"header1", "header2"},
				entity.getHeaders().getAccessControlAllowHeaders().toArray());
		assertArrayEquals(new String[] {"header3", "header4"},
				entity.getHeaders().getAccessControlExposeHeaders().toArray());
		assertEquals(false, entity.getHeaders().getAccessControlAllowCredentials());
		assertEquals(123, entity.getHeaders().getAccessControlMaxAge());
	}
