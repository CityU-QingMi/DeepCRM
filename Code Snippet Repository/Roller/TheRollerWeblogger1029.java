    private void renderThrowable(BSFException ex, Writer writer) {
        PrintWriter pw = new PrintWriter(writer);
        if (ex.getTargetException() != null) {
            pw.println("<p><b>Exception</b>: "+ex.getTargetException()
                + "<br /><b>Message</b>: "+ex.getTargetException().getMessage()+"</p>");
        } else {
            pw.println("<p><b>Exception</b>: "+ex
                + "<br /><b>Message</b>: "+ex.getMessage()+"</p>");
        }
        pw.flush();
    }
