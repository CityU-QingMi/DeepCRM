    private void initClassLoader(JspCompilationContext clctxt)
            throws IOException {

        classPath = getClassPath();

        ClassLoader jspcLoader = getClass().getClassLoader();
        // Turn the classPath into URLs
        ArrayList urls = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(classPath,
                File.pathSeparator);
        while (tokenizer.hasMoreTokens()) {
            String path = tokenizer.nextToken();
            try {
                File libFile = new File(path);
                urls.add(libFile.toURL());
            } catch (IOException ioe) {
                // Failing a toCanonicalPath on a file that
                // exists() should be a JVM regression test,
                // therefore we have permission to freak uot
                throw new RuntimeException(ioe.toString());
            }
        }

        //TODO: add .tld files to the URLCLassLoader

        URL urlsA[] = new URL[urls.size()];
        urls.toArray(urlsA);
        loader = new URLClassLoader(urlsA, this.getClass().getClassLoader());

    }
