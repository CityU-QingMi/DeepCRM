		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws ServletException {

			if (request.getAttribute("test2") != null) {
				throw new ServletException("Wrong interceptor order");
			}
			request.setAttribute("test1", "test1");
			request.setAttribute("test1x", "test1x");
			request.setAttribute("test1y", "test1y");
			return true;
		}
