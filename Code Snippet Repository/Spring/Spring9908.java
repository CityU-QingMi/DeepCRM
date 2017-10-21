	public Map<String, String> decodePathVariables(HttpServletRequest request, Map<String, String> vars) {
		if (this.urlDecode) {
			return vars;
		}
		else {
			Map<String, String> decodedVars = new LinkedHashMap<>(vars.size());
			for (Entry<String, String> entry : vars.entrySet()) {
				decodedVars.put(entry.getKey(), decodeInternal(request, entry.getValue()));
			}
			return decodedVars;
		}
	}
