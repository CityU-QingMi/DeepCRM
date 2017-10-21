    public static InputStream getInputStream(String fname, JarFile jarFile,
                                             JspCompilationContext ctxt,
                                             ErrorDispatcher err)
            throws JasperException, IOException {

        InputStream in = null;


        if (jarFile != null) {
            String jarEntryName = fname.substring(1, fname.length());
            ZipEntry jarEntry = jarFile.getEntry(jarEntryName);
            if (jarEntry == null) {
                err.jspError("jsp.error.file.not.found", fname);
            }
            in = jarFile.getInputStream(jarEntry);
        } else {
            in = ctxt.getResourceAsStream(fname);
        }

        if (in == null) {
            err.jspError("jsp.error.file.not.found", fname);
        }

        return in;
    }
