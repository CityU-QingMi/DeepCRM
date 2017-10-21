		public Mono<ServerResponse> parts(ServerRequest request) {
			return request.body(BodyExtractors.toParts()).collectList()
					.flatMap(parts -> {
						try {
							assertEquals(2, parts.size());
							assertEquals("foo.txt", ((FilePart) parts.get(0)).filename());
							assertEquals("bar", ((FormFieldPart) parts.get(1)).value());
						}
						catch(Exception e) {
							return Mono.error(e);
						}
						return ServerResponse.ok().build();
					});
		}
