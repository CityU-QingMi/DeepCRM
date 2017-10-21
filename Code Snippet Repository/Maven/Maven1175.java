    public void testInterpolationOfLegacyExpressionsThatDontIncludeTheProjectPrefix()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "unprefixed-expression-interpolation/child" );

        assertEquals( pom.getBasedir(), new File( pom.getValue( "properties/projectDir" ).toString() ) );

        assertEquals( "org.apache.maven.its.mng3831.child", pom.getValue( "properties/projectGroupId" ) );
        assertEquals( "child", pom.getValue( "properties/projectArtifactId" ) );
        assertEquals( "2.0-alpha-1", pom.getValue( "properties/projectVersion" ) );
        assertEquals( "jar", pom.getValue( "properties/projectPackaging" ) );

        assertEquals( "child-name", pom.getValue( "properties/projectName" ) );
        assertEquals( "child-desc", pom.getValue( "properties/projectDesc" ) );
        assertEquals( "http://child.org/", pom.getValue( "properties/projectUrl" ) );
        assertEquals( "2008", pom.getValue( "properties/projectYear" ) );
        assertEquals( "child-org-name", pom.getValue( "properties/projectOrgName" ) );

        assertEquals( "2.0.0", pom.getValue( "properties/projectPrereqMvn" ) );
        assertEquals( "http://scm.org/", pom.getValue( "properties/projectScmUrl" ) );
        assertEquals( "http://issue.org/", pom.getValue( "properties/projectIssueUrl" ) );
        assertEquals( "http://ci.org/", pom.getValue( "properties/projectCiUrl" ) );
        assertEquals( "child-dist-repo", pom.getValue( "properties/projectDistRepoName" ) );
        assertEquals( "http://dist.org/", pom.getValue( "properties/projectDistRepoUrl" ) );
        assertEquals( "http://site.org/", pom.getValue( "properties/projectDistSiteUrl" ) );

        assertEquals( "org.apache.maven.its.mng3831", pom.getValue( "properties/parentGroupId" ) );
        assertEquals( "parent", pom.getValue( "properties/parentArtifactId" ) );
        assertEquals( "1.0", pom.getValue( "properties/parentVersion" ) );

        assertTrue( pom.getValue( "properties/projectBuildOut" ).toString().endsWith( "bin" ) );
        assertTrue( pom.getValue( "properties/projectSiteOut" ).toString().endsWith( "doc" ) );
    }
