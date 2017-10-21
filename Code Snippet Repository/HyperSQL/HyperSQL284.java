    private void errprintln(String s) {
        if (pwQuery != null && htmlMode) {
            pwQuery.println("<DIV class=\"sqltool-error\"><CODE>"
                    + SqlFile.escapeHtml(s) + "</CODE></DIV>");
            pwQuery.flush();
        }
        if (shared.psStd != null && htmlMode) {
            shared.psStd.println("<DIV class=\"sqltool-error\"><CODE>"
                    + SqlFile.escapeHtml(s) + "</CODE></DIV>");
        } else {
            logger.privlog(Level.SEVERE, s, null, 4, SqlFile.class);
/**/
/**/
/**/
/**/
        }
    }
