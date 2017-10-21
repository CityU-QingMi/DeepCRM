		@Override
		public void postHandle(
				HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)
				throws ServletException {

			if (request.getParameter("noView") != null) {
				modelAndView.clear();
			}
			if (request.getAttribute("test1x") == null) {
				throw new ServletException("Wrong interceptor order");
			}
			if (!"test2x".equals(request.getAttribute("test2x"))) {
				throw new ServletException("Incorrect request attribute");
			}
			request.removeAttribute("test2x");
		}
