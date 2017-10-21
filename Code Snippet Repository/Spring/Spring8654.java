		@Override
		public void afterConfigurerAdded(WebTestClient.Builder builder,
				@Nullable WebHttpHandlerBuilder httpHandlerBuilder,
				@Nullable ClientHttpConnector connector) {

			Assert.notNull(httpHandlerBuilder, "Not a mock server");
			httpHandlerBuilder.filters(filters -> {
				filters.removeIf(filter -> filter instanceof IdentityFilter);
				filters.add(0, this.filter);
			});
		}
