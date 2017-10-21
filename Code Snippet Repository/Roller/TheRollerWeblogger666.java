    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response)
            throws ServletException, IOException {
        
        Weblogger roller = WebloggerFactory.getWeblogger();
        try {
            WeblogEntryManager wmgr = roller.getWeblogEntryManager();
            WeblogEntryComment c = wmgr.getComment(request.getParameter("id"));
            if (c == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                // need post permission to view comments
                RollerSession rses = RollerSession.getRollerSession(request);
                Weblog weblog = c.getWeblogEntry().getWebsite();
                if (weblog.hasUserPermission(rses.getAuthenticatedUser(), WeblogPermission.POST)) {
                    String content = Utilities.escapeHTML(c.getContent());
                    content = WordUtils.wrap(content, 72);
                    content = StringEscapeUtils.escapeEcmaScript(content);
                    String json = "{ id: \"" + c.getId() + "\"," + "content: \"" + content + "\" }";
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/html; charset=utf-8");
                    response.getWriter().print(json);
                    response.flushBuffer();
                    response.getWriter().flush();
                    response.getWriter().close();
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
