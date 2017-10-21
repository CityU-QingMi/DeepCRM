	private void parseLocaleCookieIfNecessary(HttpServletRequest request) {
		if (request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) == null) {
			Locale locale = null;
			TimeZone timeZone = null;

			// Retrieve and parse cookie value.
			String cookieName = getCookieName();
			if (cookieName != null) {
				Cookie cookie = WebUtils.getCookie(request, cookieName);
				if (cookie != null) {
					String value = cookie.getValue();
					String localePart = value;
					String timeZonePart = null;
					int spaceIndex = localePart.indexOf(' ');
					if (spaceIndex != -1) {
						localePart = value.substring(0, spaceIndex);
						timeZonePart = value.substring(spaceIndex + 1);
					}
					try {
						locale = (!"-".equals(localePart) ? parseLocaleValue(localePart) : null);
						if (timeZonePart != null) {
							timeZone = StringUtils.parseTimeZoneString(timeZonePart);
						}
					}
					catch (IllegalArgumentException ex) {
						if (request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) != null) {
							// Error dispatch: ignore locale/timezone parse exceptions
							if (logger.isDebugEnabled()) {
								logger.debug("Ignoring invalid locale cookie '" + cookieName +
										"' with value [" + value + "] due to error dispatch: " + ex.getMessage());
							}
						}
						else {
							throw new IllegalStateException("Invalid locale cookie '" + cookieName +
									"' with value [" + value + "]: " + ex.getMessage());
						}
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Parsed cookie value [" + cookie.getValue() + "] into locale '" + locale +
								"'" + (timeZone != null ? " and time zone '" + timeZone.getID() + "'" : ""));
					}
				}
			}

			request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME,
					(locale != null ? locale : determineDefaultLocale(request)));
			request.setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME,
					(timeZone != null ? timeZone : determineDefaultTimeZone(request)));
		}
	}
