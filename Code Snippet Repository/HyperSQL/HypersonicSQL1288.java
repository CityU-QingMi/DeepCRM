    private void writeHeader(PrintWriter pWriter, String filePath)
            throws BadSpecial, SqlToolError {
        char[] readBfr = new char[1024];
        int i;
        StringWriter sWriter = new StringWriter();
        InputStreamReader isr = null;
        String str;
        try {
            InputStream is = (topHtmlFile == null)
                    ? getClass().getResourceAsStream(
                    "sqltool/top-boilerplate.html")
                    : new FileInputStream(topHtmlFile);
            if (is == null)
                throw new IOException("Missing resource: "
                    + ((topHtmlFile == null)
                    ? topHtmlFile
                    : "sqltool/top-boilerplate"));
            isr = new InputStreamReader(is);
            while ((i = isr.read(readBfr)) > -1)
                sWriter.write(readBfr, 0, i);
            readBfr = null;
            str = sWriter.toString();
            sWriter.close();
        } catch (Exception e) {
            throw new BadSpecial(
                    SqltoolRB.file_writefail.getString(filePath), e);
        } finally {
            try {
                if (isr != null) isr.close();
            } catch (IOException ioe) {
                // TODO: Throw appropriate exception
            }
        }
        pWriter.write(dereference(str.replaceAll("\\r?\\n", LS), true));
    }
