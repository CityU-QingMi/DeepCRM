	private static MultiValueMap<String, MediaType> parseMimeTypes() {
		InputStream is = null;
		try {
			is = MediaTypeFactory.class.getResourceAsStream(MIME_TYPES_FILE_NAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.US_ASCII));
			MultiValueMap<String, MediaType> result = new LinkedMultiValueMap<>();
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty() || line.charAt(0) == '#') {
					continue;
				}
				String[] tokens = StringUtils.tokenizeToStringArray(line, " \t\n\r\f");
				MediaType mediaType = MediaType.parseMediaType(tokens[0]);
				for (int i = 1; i < tokens.length; i++) {
					String fileExtension = tokens[i].toLowerCase(Locale.ENGLISH);
					result.add(fileExtension, mediaType);
				}
			}
			return result;
		}
		catch (IOException ex) {
			throw new IllegalStateException("Could not load '" + MIME_TYPES_FILE_NAME + "'", ex);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException ignore) {
				}
			}
		}
	}
