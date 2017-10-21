        @Override
        public RoutingAppender build() {
            final String name = getName();
            if (name == null) {
                LOGGER.error("No name defined for this RoutingAppender");
                return null;
            }
            if (routes == null) {
                LOGGER.error("No routes defined for RoutingAppender {}", name);
                return null;
            }
            return new RoutingAppender(name, getFilter(), isIgnoreExceptions(), routes, rewritePolicy,
                    getConfiguration(), purgePolicy, defaultRouteScript);
        }
