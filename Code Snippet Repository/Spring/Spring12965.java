		@RequestMapping(value = "", method = POST, consumes = {"", ""})
		public ResponseEntity<Object> create(@RequestPart(name = "json-data") TestData testData,
				@RequestPart("file-data") Optional<MultipartFile> file,
				@RequestPart(name = "empty-data", required = false) TestData emptyData,
				@RequestPart(name = "iso-8859-1-data") byte[] iso88591Data) {

			Assert.assertArrayEquals(new byte[]{(byte) 0xC4}, iso88591Data);

			String url = "http://localhost:8080/test/" + testData.getName() + "/" + file.get().getOriginalFilename();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(url));
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
