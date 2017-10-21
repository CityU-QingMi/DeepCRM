	@Override
	protected HierarchicalUriComponents expandInternal(UriTemplateVariables uriVariables) {
		Assert.state(!this.encoded, "Cannot expand an already encoded UriComponents object");

		String schemeTo = expandUriComponent(getScheme(), uriVariables);
		String fragmentTo = expandUriComponent(getFragment(), uriVariables);
		String userInfoTo = expandUriComponent(this.userInfo, uriVariables);
		String hostTo = expandUriComponent(this.host, uriVariables);
		String portTo = expandUriComponent(this.port, uriVariables);
		PathComponent pathTo = this.path.expand(uriVariables);
		MultiValueMap<String, String> paramsTo = expandQueryParams(uriVariables);

		return new HierarchicalUriComponents(schemeTo, fragmentTo, userInfoTo, hostTo, portTo,
				pathTo, paramsTo, false, false);
	}
