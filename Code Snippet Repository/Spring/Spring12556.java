		@Override
		public void afterCompletion(
				HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {

			if (request.getAttribute("test1y") == null) {
				throw new ServletException("Wrong interceptor order");
			}
			if (request.getAttribute("test2y") == null) {
				throw new ServletException("afterCompletion invoked twice");
			}
			request.removeAttribute("test2y");
		}
