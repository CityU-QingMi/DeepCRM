		@PostMapping("")
		void requestPart(
				@RequestPart FormFieldPart fieldPart,
				@RequestPart("fileParts") FilePart fileParts,
				@RequestPart("fileParts") Mono<FilePart> filePartsMono,
				@RequestPart("fileParts") Flux<FilePart> filePartsFlux,
				@RequestPart("jsonPart") Person person,
				@RequestPart("jsonPart") Mono<Person> personMono) {

			assertEquals("fieldValue", fieldPart.value());
			assertEquals("fileParts:foo.txt", partDescription(fileParts));
			assertEquals("fileParts:foo.txt", partDescription(filePartsMono.block()));
			assertEquals("[fileParts:foo.txt,fileParts:logo.png]", partFluxDescription(filePartsFlux).block());
			assertEquals("Jason", person.getName());
			assertEquals("Jason", personMono.block().getName());
		}
