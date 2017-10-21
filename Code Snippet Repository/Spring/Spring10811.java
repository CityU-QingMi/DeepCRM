		private List<MethodParameter> applyFilters() {
			List<MethodParameter> matches = new ArrayList<>();
			for (int i = 0; i < method.getParameterCount(); i++) {
				MethodParameter param = new SynthesizingMethodParameter(method, i);
				param.initParameterNameDiscovery(nameDiscoverer);
				if (this.filters.stream().allMatch(p -> p.test(param))) {
					matches.add(param);
				}
			}
			return matches;
		}
