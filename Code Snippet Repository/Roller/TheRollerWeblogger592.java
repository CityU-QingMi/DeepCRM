    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/html; charset=utf-8");

        // Convince proxies and IE not to cache this.
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:00 GMT");

        PrintWriter out = response.getWriter();
        out.println(this.authenticator.getHtml(request));
    }
