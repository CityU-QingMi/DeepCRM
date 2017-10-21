    private void setArtifactProperties( ArtifactDescriptorResult result, Model model )
    {
        String downloadUrl = null;
        DistributionManagement distMgmt = model.getDistributionManagement();
        if ( distMgmt != null )
        {
            downloadUrl = distMgmt.getDownloadUrl();
        }
        if ( downloadUrl != null && downloadUrl.length() > 0 )
        {
            Artifact artifact = result.getArtifact();
            Map<String, String> props = new HashMap<>( artifact.getProperties() );
            props.put( ArtifactProperties.DOWNLOAD_URL, downloadUrl );
            result.setArtifact( artifact.setProperties( props ) );
        }
    }
