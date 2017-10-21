	public static void saveOutputFlashMap(String location, HttpServletRequest request, HttpServletResponse response) {
		FlashMap flashMap = getOutputFlashMap(request);
		if (CollectionUtils.isEmpty(flashMap)) {
			return;
		}

		UriComponents uriComponents = UriComponentsBuilder.fromUriString(location).build();
		flashMap.setTargetRequestPath(uriComponents.getPath());
		flashMap.addTargetRequestParams(uriComponents.getQueryParams());

		FlashMapManager manager = getFlashMapManager(request);
		Assert.state(manager != null, "No FlashMapManager. Is this a DispatcherServlet handled request?");
		manager.saveOutputFlashMap(flashMap, request, response);
	}
