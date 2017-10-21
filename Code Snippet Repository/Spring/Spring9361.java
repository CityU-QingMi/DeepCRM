	@Override
	@SuppressWarnings("")
	protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		if (object instanceof ResourceRegion) {
			writeResourceRegion((ResourceRegion) object, outputMessage);
		}
		else {
			Collection<ResourceRegion> regions = (Collection<ResourceRegion>) object;
			if (regions.size() == 1) {
				writeResourceRegion(regions.iterator().next(), outputMessage);
			}
			else {
				writeResourceRegionCollection((Collection<ResourceRegion>) object, outputMessage);
			}
		}
	}
