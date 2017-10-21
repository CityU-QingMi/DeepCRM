		@Override
		public void postHandle(
				HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)
				throws ServletException {

			if (request.getAttribute("test2x") != null) {
				throw new ServletException("Wrong interceptor order");
			}
			if (!"test1x".equals(request.getAttribute("test1x"))) {
				throw new ServletException("Incorrect request attribute");
			}
			request.removeAttribute("test1x");
		}
