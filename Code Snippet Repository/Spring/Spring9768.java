	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		for (Object c : this.contributors) {
			if (c instanceof UriComponentsContributor) {
				UriComponentsContributor contributor = (UriComponentsContributor) c;
				if (contributor.supportsParameter(parameter)) {
					return true;
				}
			}
			else if (c instanceof HandlerMethodArgumentResolver) {
				if (((HandlerMethodArgumentResolver) c).supportsParameter(parameter)) {
					return false;
				}
			}
		}
		return false;
	}
