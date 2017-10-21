	public static boolean isMobileDevice(HttpServletRequest request) {

		// String userAgent = request.getHeader("User-Agent");
		// System.out.println(userAgent);

		Device device = DeviceUtils.getCurrentDevice(request);

		if (device == null) {
			// log.info("no device detected");
			return false;
		} else if (device.isNormal()) {
			// log.info("Device is normal");
			return false;
		} else if (device.isMobile()) {
			// log.info("Device is mobile");
			// System.out.println(userAgent);
			return true;
		} else if (device.isTablet()) {
			// log.info("Device is tablet");
			// System.out.println(userAgent);
			return true;
		} else {
			return false;
		}

	}
