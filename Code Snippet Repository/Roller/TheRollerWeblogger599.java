	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedTimeMillis,
			MobileDeviceRepository.DeviceType deviceType) {

		// Save our device type for device switching. Must use chaching on
		// headers for this to work.
		if (deviceType != null) {

			// int code = new HashCodeBuilder().append(deviceType.name())
			// .hashCode();
			// String eTag = String.valueOf(code);

			String eTag = deviceType.name();

			response.setHeader("ETag", eTag);
		}

		response.setDateHeader("Last-Modified", lastModifiedTimeMillis);
		// Force clients to revalidate each time
		// See RFC 2616 (HTTP 1.1 spec) secs 14.21, 13.2.1
		response.setDateHeader("Expires", 0);
		// We may also want this (See 13.2.1 and 14.9.4)
		// response.setHeader("Cache-Control","must-revalidate");

	}
