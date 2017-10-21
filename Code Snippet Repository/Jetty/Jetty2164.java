    @Override
    public void addClassPath(String classPath) throws IOException
    {

        StringTokenizer tokenizer = new StringTokenizer(classPath, ",;");
        while (tokenizer.hasMoreTokens())
        {
            String path = tokenizer.nextToken();
            Resource resource = getContext().newResource(path);

            // Resolve file path if possible
            File file = resource.getFile();
            if (file != null && isAcceptableLibrary(file, JAR_WITH_SUCH_CLASS_MUST_BE_EXCLUDED))
            {
                super.addClassPath(path);
            }
            else
            {
                __logger.info("Did not add " + path + " to the classloader of the webapp " + getContext());
            }
        }

    }
