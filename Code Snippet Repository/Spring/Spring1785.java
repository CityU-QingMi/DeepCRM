	public static CandidateComponentsMetadata read(InputStream in) throws IOException {
		CandidateComponentsMetadata result = new CandidateComponentsMetadata();
		Properties props = new Properties();
		props.load(in);
		props.forEach((type, value) -> {
			Set<String> candidates = new HashSet<>(Arrays.asList(((String) value).split(",")));
			result.add(new ItemMetadata((String) type, candidates));
		});
		return result;
	}
