	public MultiValueMap<String, String> decodeMatrixVariables(HttpServletRequest request, MultiValueMap<String, String> vars) {
		if (this.urlDecode) {
			return vars;
		}
		else {
			MultiValueMap<String, String> decodedVars = new LinkedMultiValueMap<>(vars.size());
			for (String key : vars.keySet()) {
				for (String value : vars.get(key)) {
					decodedVars.add(key, decodeInternal(request, value));
				}
			}
			return decodedVars;
		}
	}
