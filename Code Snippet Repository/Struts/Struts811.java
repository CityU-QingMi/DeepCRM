    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);

        // Cache?
        if (!cache) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0); // Proxies
        }

        //set contenttype @see ww-4564
        response.setContentType("text/html");
        
        // Render
        PrintWriter pw = new PrintWriter(response.getOutputStream());
        pw.write("<!DOCTYPE html><html><body><form action=\"" + finalLocation + "\" method=\"POST\">");
        writeFormElements(request, pw);
        writePrologueScript(pw);
        pw.write("</html>");
        pw.flush();
    }
