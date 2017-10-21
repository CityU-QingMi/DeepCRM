    private List<File> getDependencyFiles()
    {
        List<File> dependencyFiles = new ArrayList<>();
        for ( Iterator<Artifact> iter = projectArtifacts.iterator(); iter.hasNext(); )
        {
            Artifact artifact = iter.next();
            
            // Include runtime and compile time libraries, and possibly test libs too
            if(artifact.getType().equals("war"))
            {
                continue;
            }
            MavenProject mavenProject = getProjectReferences( artifact, project );
            if (mavenProject != null)
            {
                File projectPath = Paths.get(mavenProject.getBuild().getOutputDirectory()).toFile();
                getLog().debug( "Adding project directory " + projectPath.toString() );
                dependencyFiles.add( projectPath );
                continue;
            }

            if (Artifact.SCOPE_PROVIDED.equals(artifact.getScope()))
                continue; //never add dependencies of scope=provided to the webapp's classpath (see also <useProvidedScope> param)

            if (Artifact.SCOPE_TEST.equals(artifact.getScope()) && !useTestScope)
                continue; //only add dependencies of scope=test if explicitly required

            dependencyFiles.add(artifact.getFile());
            getLog().debug( "Adding artifact " + artifact.getFile().getName() + " with scope "+artifact.getScope()+" for WEB-INF/lib " );   
        }
              
        return dependencyFiles; 
    }
