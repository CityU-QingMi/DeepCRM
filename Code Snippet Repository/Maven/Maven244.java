    boolean checkScopeUpdate( ResolutionNode farthest, ResolutionNode nearest, List<ResolutionListener> listeners )
    {
        boolean updateScope = false;
        Artifact farthestArtifact = farthest.getArtifact();
        Artifact nearestArtifact = nearest.getArtifact();

        /* farthest is runtime and nearest has lower priority, change to runtime */
        if ( Artifact.SCOPE_RUNTIME.equals( farthestArtifact.getScope() )
                 && ( Artifact.SCOPE_TEST.equals( nearestArtifact.getScope() )
                      || Artifact.SCOPE_PROVIDED.equals( nearestArtifact.getScope() ) ) )
        {
            updateScope = true;
        }

        /* farthest is compile and nearest is not (has lower priority), change to compile */
        if ( Artifact.SCOPE_COMPILE.equals( farthestArtifact.getScope() )
                 && !Artifact.SCOPE_COMPILE.equals( nearestArtifact.getScope() ) )
        {
            updateScope = true;
        }

        /* current POM rules all, if nearest is in current pom, do not update its artifactScope */
        if ( ( nearest.getDepth() < 2 ) && updateScope )
        {
            updateScope = false;

            fireEvent( ResolutionListener.UPDATE_SCOPE_CURRENT_POM, listeners, nearest, farthestArtifact );
        }

        if ( updateScope )
        {
            fireEvent( ResolutionListener.UPDATE_SCOPE, listeners, nearest, farthestArtifact );

            // previously we cloned the artifact, but it is more efficient to just update the artifactScope
            // if problems are later discovered that the original object needs its original artifactScope value, 
            // cloning may
            // again be appropriate
            nearestArtifact.setScope( farthestArtifact.getScope() );
        }

        return updateScope;
    }
