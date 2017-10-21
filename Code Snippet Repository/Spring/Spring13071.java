		@RequestMapping(path = "", method = RequestMethod.POST)
		public ResponseEntity<String> foo(HttpEntity<byte[]> requestEntity) throws Exception {
			assertNotNull(requestEntity);
			assertEquals("MyValue", requestEntity.getHeaders().getFirst("MyRequestHeader"));

			String body = new String(requestEntity.getBody(), "UTF-8");
			assertEquals("Hello World", body);

			URI location = new URI("/foo");
			return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body(body);
		}
