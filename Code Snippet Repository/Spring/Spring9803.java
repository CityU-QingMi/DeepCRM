	private static List<Part> resolvePartList(HttpServletRequest servletRequest, String name) throws Exception {
		Collection<Part> parts = servletRequest.getParts();
		List<Part> result = new ArrayList<>(parts.size());
		for (Part part : parts) {
			if (part.getName().equals(name)) {
				result.add(part);
			}
		}
		return result;
	}
