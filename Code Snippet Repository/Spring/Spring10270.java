		private Mono<Void> assertGetFormParts(ServerWebExchange exchange) {
			return exchange
					.getMultipartData()
					.doOnNext(parts -> {
						assertEquals(2, parts.size());
						assertTrue(parts.containsKey("fooPart"));
						assertFooPart(parts.getFirst("fooPart"));
						assertTrue(parts.containsKey("barPart"));
						assertBarPart(parts.getFirst("barPart"));
					})
					.then();
		}
