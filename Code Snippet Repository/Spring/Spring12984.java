	@SuppressWarnings("")
	public void handle(
			@RequestPart SimpleBean requestPart,
			@RequestPart(value="requestPart", required=false) SimpleBean namedRequestPart,
			@Valid @RequestPart("requestPart") SimpleBean validRequestPart,
			@RequestPart("requestPart") MultipartFile multipartFile,
			@RequestPart("requestPart") List<MultipartFile> multipartFileList,
			@RequestPart("requestPart") MultipartFile[] multipartFileArray,
			int i,
			MultipartFile multipartFileNotAnnot,
			Part part,
			@RequestPart("requestPart") List<Part> partList,
			@RequestPart("requestPart") Part[] partArray,
			@RequestParam MultipartFile requestParamAnnot,
			Optional<MultipartFile> optionalMultipartFile,
			@RequestPart("requestPart") Optional<List<MultipartFile>> optionalMultipartFileList,
			Optional<Part> optionalPart,
			@RequestPart("requestPart") Optional<List<Part>> optionalPartList,
			@RequestPart("requestPart") Optional<SimpleBean> optionalRequestPart) {
	}
