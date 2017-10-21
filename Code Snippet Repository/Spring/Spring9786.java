	protected void cleanupFileItems(MultiValueMap<String, MultipartFile> multipartFiles) {
		for (List<MultipartFile> files : multipartFiles.values()) {
			for (MultipartFile file : files) {
				if (file instanceof CommonsMultipartFile) {
					CommonsMultipartFile cmf = (CommonsMultipartFile) file;
					cmf.getFileItem().delete();
					if (logger.isDebugEnabled()) {
						logger.debug("Cleaning up multipart file [" + cmf.getName() + "] with original filename [" +
								cmf.getOriginalFilename() + "], stored " + cmf.getStorageDescription());
					}
				}
			}
		}
	}
