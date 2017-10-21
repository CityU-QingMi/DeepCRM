    public static Artifact copyArtifact( Artifact artifact )
    {
        VersionRange range = artifact.getVersionRange();

        // For some reason with the introduction of MNG-1577 we have the case in Yoko where a depMan section has
        // something like the following:
        //
        // <dependencyManagement>
        //     <dependencies>
        //         <!--  Yoko modules -->
        //         <dependency>
        //             <groupId>org.apache.yoko</groupId>
        //             <artifactId>yoko-core</artifactId>
        //             <version>${version}</version>
        //         </dependency>
        // ...
        //
        // And the range is not set so we'll check here and set it. jvz.

        if ( range == null )
        {
            range = VersionRange.createFromVersion( artifact.getVersion() );
        }

        DefaultArtifact clone = new DefaultArtifact( artifact.getGroupId(), artifact.getArtifactId(), range.cloneOf(),
            artifact.getScope(), artifact.getType(), artifact.getClassifier(),
            artifact.getArtifactHandler(), artifact.isOptional() );
        clone.setRelease( artifact.isRelease() );
        clone.setResolvedVersion( artifact.getVersion() );
        clone.setResolved( artifact.isResolved() );
        clone.setFile( artifact.getFile() );

        clone.setAvailableVersions( copyList( artifact.getAvailableVersions() ) );
        if ( artifact.getVersion() != null )
        {
            clone.setBaseVersion( artifact.getBaseVersion() );
        }
        clone.setDependencyFilter( artifact.getDependencyFilter() );
        clone.setDependencyTrail( copyList( artifact.getDependencyTrail() ) );
        clone.setDownloadUrl( artifact.getDownloadUrl() );
        clone.setRepository( artifact.getRepository() );

        return clone;
    }
