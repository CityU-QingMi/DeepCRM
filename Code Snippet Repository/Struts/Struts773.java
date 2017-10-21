        public Locale find() {
            Locale requestOnlyLocale = super.find();

            if (requestOnlyLocale != null) {
                LOG.debug("Found locale under request only param, it won't be stored in session!");
                shouldStore = false;
                return requestOnlyLocale;
            }

            LOG.debug("Searching locale in request under parameter {}", parameterName);
            Parameter requestedLocale = findLocaleParameter(actionInvocation, parameterName);
            if (requestedLocale.isDefined()) {
                return getLocaleFromParam(requestedLocale.getValue());
            }

            return null;
        }
