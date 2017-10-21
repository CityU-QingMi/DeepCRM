		@Override
		public PathComponent encode(Charset charset) {
			List<String> pathSegments = getPathSegments();
			List<String> encodedPathSegments = new ArrayList<>(pathSegments.size());
			for (String pathSegment : pathSegments) {
				String encodedPathSegment = encodeUriComponent(pathSegment, charset, Type.PATH_SEGMENT);
				encodedPathSegments.add(encodedPathSegment);
			}
			return new PathSegmentComponent(encodedPathSegments);
		}
