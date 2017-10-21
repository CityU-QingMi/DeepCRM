        public Locale find() {
            LOG.debug("Searching locale in request under parameter {}", requestOnlyParameterName);

            Parameter requestedLocale = findLocaleParameter(actionInvocation, requestOnlyParameterName);
            if (requestedLocale.isDefined()) {
                return getLocaleFromParam(requestedLocale.getValue());
            }

            return null;
        }
