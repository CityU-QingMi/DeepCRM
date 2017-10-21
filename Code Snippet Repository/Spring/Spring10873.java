	@SuppressWarnings({"", ""})
	public void handle(
			@RequestParam(name = "name", defaultValue = "bar") String param1,
			@RequestParam("name") String[] param2,
			@RequestParam("name") Map<?, ?> param3,
			@RequestParam("mfile") MultipartFile param4,
			@RequestParam("mfilelist") List<MultipartFile> param5,
			@RequestParam("mfilearray") MultipartFile[] param6,
			@RequestParam("pfile") Part param7,
			@RequestParam("pfilelist") List<Part> param8,
			@RequestParam("pfilearray") Part[] param9,
			@RequestParam Map<?, ?> param10,
			String stringNotAnnot,
			MultipartFile multipartFileNotAnnot,
			List<MultipartFile> multipartFileList,
			Part part,
			@RequestPart MultipartFile requestPartAnnot,
			@RequestParam("name") String paramRequired,
			@RequestParam(name = "name", required = false) String paramNotRequired,
			@RequestParam("name") Optional<Integer> paramOptional,
			@RequestParam("name") Optional<Integer[]> paramOptionalArray,
			@RequestParam("name") Optional<List> paramOptionalList,
			@RequestParam("mfile") Optional<MultipartFile> multipartFileOptional) {
	}
