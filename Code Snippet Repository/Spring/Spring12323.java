		@Override
		public Locale getLocale() {
			HttpSession session = this.request.getSession(false);
			if (session != null) {
				Object localeObject = Config.get(session, Config.FMT_LOCALE);
				if (localeObject instanceof Locale) {
					return (Locale) localeObject;
				}
			}
			return RequestContextUtils.getLocale(this.request);
		}
