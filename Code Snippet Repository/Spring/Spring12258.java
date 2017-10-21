	@Override
	public Resource transform(HttpServletRequest request, Resource resource,
			ResourceTransformerChain chain) throws IOException {

		resource = chain.transform(request, resource);
		if (!this.fileExtension.equals(StringUtils.getFilenameExtension(resource.getFilename()))) {
			return resource;
		}

		byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
		String content = new String(bytes, DEFAULT_CHARSET);

		if (!content.startsWith(MANIFEST_HEADER)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Manifest should start with 'CACHE MANIFEST', skip: " + resource);
			}
			return resource;
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Transforming resource: " + resource);
		}

		Scanner scanner = new Scanner(content);
		LineInfo previous = null;
		LineAggregator aggregator = new LineAggregator(resource, content);

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			LineInfo current = new LineInfo(line, previous);
			LineOutput lineOutput = processLine(current, request, resource, chain);
			aggregator.add(lineOutput);
			previous = current;
		}

		return aggregator.createResource();
	}
