		private static List<PathPattern> parse(String[] paths, PathPatternParser parser) {
			return Arrays
					.stream(paths)
					.map(path -> {
						if (StringUtils.hasText(path) && !path.startsWith("/")) {
							path = "/" + path;
						}
						return parser.parse(path);
					})
					.collect(Collectors.toList());
		}
