        public ActionConfig build() {
            target.params = Collections.unmodifiableMap(target.params);
            target.results = Collections.unmodifiableMap(target.results);
            target.interceptors = Collections.unmodifiableList(target.interceptors);
            target.exceptionMappings = Collections.unmodifiableList(target.exceptionMappings);
            target.allowedMethods = AllowedMethods.build(target.strictMethodInvocation, allowedMethods, methodRegex != null ? methodRegex : DEFAULT_METHOD_REGEX);

            ActionConfig result = target;
            target = new ActionConfig(target);
            return result;
        }
