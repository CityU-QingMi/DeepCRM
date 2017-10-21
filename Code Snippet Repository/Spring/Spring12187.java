	@SuppressWarnings("")
	private Collection<MediaType> getMediaTypes(NativeWebRequest request)
			throws HttpMediaTypeNotAcceptableException {

		Collection<MediaType> mediaTypes = (Collection<MediaType>) request.getAttribute(
				HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

		return CollectionUtils.isEmpty(mediaTypes) ?
				this.contentNegotiationManager.resolveMediaTypes(request) : mediaTypes;
	}
