	@Nullable
	private String[] getParameterNames(List<KParameter> parameters) {
		List<KParameter> filteredParameters = parameters
				.stream()
				.filter(p -> KParameter.Kind.VALUE.equals(p.getKind()))
				.collect(Collectors.toList());
		String[] parameterNames = new String[filteredParameters.size()];
		for (int i = 0; i < filteredParameters.size(); i++) {
			String name = filteredParameters.get(i).getName();
			if (name == null) {
				return null;
			}
			parameterNames[i] = name;
		}
		return parameterNames;
	}
