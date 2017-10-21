    private void validatePrerequisitesForNonMavenPluginProjects( List<MavenProject> projects )
    {
        for ( MavenProject mavenProject : projects )
        {
            if ( !"maven-plugin".equals( mavenProject.getPackaging() ) )
            {
                Prerequisites prerequisites = mavenProject.getPrerequisites();
                if ( prerequisites != null && prerequisites.getMaven() != null )
                {
                    logger.warn( "The project " + mavenProject.getId() + " uses prerequisites"
                        + " which is only intended for maven-plugin projects "
                        + "but not for non maven-plugin projects. "
                        + "For such purposes you should use the maven-enforcer-plugin. "
                        + "See https://maven.apache.org/enforcer/enforcer-rules/requireMavenVersion.html" );
                }
            }
        }
    }
