    private List<URL> setUpWebAppClassPath() throws Exception
    {
        //add any classes from the webapp
        List<URL> urls = new ArrayList<URL>();
        String classesDir = classesDirectory.getCanonicalPath();
        classesDir = classesDir + (classesDir.endsWith(File.pathSeparator) ? "" : File.separator);
        urls.add(Resource.toURL(new File(classesDir)));

        if (getLog().isDebugEnabled())
            getLog().debug("Adding to classpath classes dir: " + classesDir);

        //add the dependencies of the webapp (which will form WEB-INF/lib)
        for (Iterator<Artifact> iter = project.getArtifacts().iterator(); iter.hasNext();)
        {
            Artifact artifact = (Artifact)iter.next();

            // Include runtime and compile time libraries
            if (!Artifact.SCOPE_TEST.equals(artifact.getScope()) && !Artifact.SCOPE_PROVIDED.equals(artifact.getScope()))
            {
                String filePath = artifact.getFile().getCanonicalPath();
                if (getLog().isDebugEnabled())
                    getLog().debug("Adding to classpath dependency file: " + filePath);

                urls.add(Resource.toURL(artifact.getFile()));
            }
        }
        return urls;
    }
