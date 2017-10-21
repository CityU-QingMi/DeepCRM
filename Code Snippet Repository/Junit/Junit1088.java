		@Test
		void methodsByFullyQualifiedNameAreStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.selectors(selectMethod(fullyQualifiedMethodName()))
					.build();
			// @formatter:on

			List<MethodSelector> methodSelectors = discoveryRequest.getSelectorsByType(MethodSelector.class);
			assertThat(methodSelectors).hasSize(1);

			MethodSelector methodSelector = methodSelectors.get(0);
			assertThat(methodSelector.getJavaClass()).isEqualTo(LauncherDiscoveryRequestBuilderTests.class);
			assertThat(methodSelector.getJavaMethod()).isEqualTo(fullyQualifiedMethod());
		}
