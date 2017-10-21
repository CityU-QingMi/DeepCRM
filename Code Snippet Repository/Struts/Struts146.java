        public RuntimeConfigurationImpl(Map<String, Map<String, ActionConfig>> namespaceActionConfigs,
                                        Map<String, String> namespaceConfigs,
                                        PatternMatcher<int[]> matcher) {
            this.namespaceActionConfigs = namespaceActionConfigs;
            this.namespaceConfigs = namespaceConfigs;

            this.namespaceActionConfigMatchers = new LinkedHashMap<>();
            this.namespaceMatcher = new NamespaceMatcher(matcher, namespaceActionConfigs.keySet());

            for (String ns : namespaceActionConfigs.keySet()) {
                namespaceActionConfigMatchers.put(ns, new ActionConfigMatcher(matcher, namespaceActionConfigs.get(ns), true));
            }
        }
