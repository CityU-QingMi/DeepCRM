	private void bindParts(HttpServletRequest request, MutablePropertyValues mpvs) {
		try {
			MultiValueMap<String, Part> map = new LinkedMultiValueMap<>();
			for (Part part : request.getParts()) {
				map.add(part.getName(), part);
			}
			for (Map.Entry<String, List<Part>> entry: map.entrySet()) {
				if (entry.getValue().size() == 1) {
					Part part = entry.getValue().get(0);
					if (isBindEmptyMultipartFiles() || part.getSize() > 0) {
						mpvs.add(entry.getKey(), part);
					}
				}
				else {
					mpvs.add(entry.getKey(), entry.getValue());
				}
			}
		}
		catch (Exception ex) {
			throw new MultipartException("Failed to get request parts", ex);
		}
	}
