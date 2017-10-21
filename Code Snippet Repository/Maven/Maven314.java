    public void testMissingArtifactMessageFormat()
    {
        String message = "Missing artifact";
        String indentation = "  ";
        String groupId = "aGroupId";
        String artifactId = "anArtifactId";
        String version = "aVersion";
        String type = "jar";
        String classifier = "aClassifier";
        String downloadUrl = "http://somewhere.com/download";
        List<String> path = Arrays.asList( "dependency1", "dependency2" );
        String expected =
            "Missing artifact" + LS + LS + "  Try downloading the file manually from: " + LS
                + "      http://somewhere.com/download" + LS + LS + "  Then, install it using the command: " + LS
                + "      mvn install:install-file -DgroupId=aGroupId -DartifactId=anArtifactId -Dversion=aVersion "
                + "-Dclassifier=aClassifier -Dpackaging=jar -Dfile=/path/to/file" + LS + LS
                + "  Alternatively, if you host your own repository you can deploy the file there: " + LS
                + "      mvn deploy:deploy-file -DgroupId=aGroupId -DartifactId=anArtifactId"
                + " -Dversion=aVersion -Dclassifier=aClassifier -Dpackaging=jar -Dfile=/path/to/file"
                + " -Durl=[url] -DrepositoryId=[id]" + LS + LS + "  Path to dependency: " + LS + "  \t1) dependency1"
                + LS + "  \t2) dependency2" + LS + LS;
        String actual =
            AbstractArtifactResolutionException.constructMissingArtifactMessage( message, indentation, groupId,
                                                                                 artifactId, version, type, classifier,
                                                                                 downloadUrl, path );
        assertEquals( expected, actual );
    }
