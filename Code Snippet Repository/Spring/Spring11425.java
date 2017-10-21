		public Mono<ServerResponse> multipartData(ServerRequest request) {
			return request
					.body(BodyExtractors.toMultipartData())
					.flatMap(map -> {
						Map<String, Part> parts = map.toSingleValueMap();
						try {
							assertEquals(2, parts.size());
							assertEquals("foo.txt", ((FilePart) parts.get("fooPart")).filename());
							assertEquals("bar", ((FormFieldPart) parts.get("barPart")).value());
						}
						catch(Exception e) {
							return Mono.error(e);
						}
						return ServerResponse.ok().build();
					});
		}
