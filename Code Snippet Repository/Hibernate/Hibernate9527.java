		@Override
		public void configure(Map configurationValues) {
			String url = (String) configurationValues.get( AvailableSettings.URL );
			if(!url.contains( "?" )) {
				url += "?";
			}
			else if(!url.endsWith( "&" )) {
				url += "&";
			}
			url += "zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";

			configurationValues.put( AvailableSettings.URL, url);
			super.configure( configurationValues );
		}
