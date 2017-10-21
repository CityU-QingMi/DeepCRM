        @Override
        public Locale find() {
            Locale requestOnlySessionLocale = super.find();

            if (requestOnlySessionLocale != null) {
                shouldStore = false;
                return requestOnlySessionLocale;
            }

            LOG.debug("Searching locale in request under parameter {}", requestCookieParameterName);
            Parameter requestedLocale = findLocaleParameter(actionInvocation, requestCookieParameterName);
            if (requestedLocale.isDefined()) {
                return getLocaleFromParam(requestedLocale.getValue());
            }

            return null;
        }
