	private void saveFlashAttributes(ModelAndViewContainer mav, NativeWebRequest request, String location) {
		mav.setRedirectModelScenario(true);
		ModelMap model = mav.getModel();
		if (model instanceof RedirectAttributes) {
			Map<String, ?> flashAttributes = ((RedirectAttributes) model).getFlashAttributes();
			if (!CollectionUtils.isEmpty(flashAttributes)) {
				HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);
				HttpServletResponse res = request.getNativeResponse(HttpServletResponse.class);
				if (req != null) {
					RequestContextUtils.getOutputFlashMap(req).putAll(flashAttributes);
					if (res != null) {
						RequestContextUtils.saveOutputFlashMap(location, req, res);
					}
				}
			}
		}
	}
