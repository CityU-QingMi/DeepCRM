		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {

			this.requestCookies = request.getCookies();
			this.requestCookies.size(); // Cause lazy loading

			response.getCookies().add("SID", ResponseCookie.from("SID", "31d4d96e407aad42")
					.path("/").secure(true).httpOnly(true).build());
			response.getCookies().add("lang", ResponseCookie.from("lang", "en-US")
					.domain("example.com").path("/").build());

			return response.setComplete();
		}
