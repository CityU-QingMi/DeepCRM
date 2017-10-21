    private void writeFooter(PrintWriter pwQuery, String filePath)
            throws SqlToolError {
        char[] readBfr = new char[1024];
        int i;
        StringWriter sWriter = new StringWriter();
        InputStreamReader isr = null;
        String str;
        try {
            InputStream is = (bottomHtmlFile == null)
                    ? getClass().getResourceAsStream(
                    "sqltool/bottom-boilerplate.html")
                    : new FileInputStream(bottomHtmlFile);
            if (is == null)
                throw new IOException("Missing resource: "
                    + ((bottomHtmlFile == null)
                    ? bottomHtmlFile
                    : "sqltool/bottom-boilerplate"));
            isr = new InputStreamReader(is);
            while ((i = isr.read(readBfr)) > -1) sWriter.write(readBfr, 0, i);
            readBfr = null;
            str = sWriter.toString();
            sWriter.close();
        } catch (Exception e) {
            throw new SqlToolError(
                    SqltoolRB.file_writefail.getString(filePath), e);
        } finally {
            try {
                if (isr != null) isr.close();
            } catch (IOException ioe) {
                // TODO: Throw appropriate exception
            }
        }
        pwQuery.write(dereference(str.replaceAll("\\r?\\n", LS), true));
    }
