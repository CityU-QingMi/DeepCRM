    public void get( Collection<? extends ArtifactDownload> artifactDownloads,
                     Collection<? extends MetadataDownload> metadataDownloads )
    {
        if ( artifactDownloads != null )
        {
            for ( ArtifactDownload download : artifactDownloads )
            {
                File remoteFile = new File( basedir, path( download.getArtifact() ) );
                try
                {
                    FileUtils.copyFile( remoteFile, download.getFile() );
                }
                catch ( IOException e )
                {
                    if ( !remoteFile.exists() )
                    {
                        download.setException( new ArtifactNotFoundException( download.getArtifact(), repository ) );
                    }
                    else
                    {
                        download.setException( new ArtifactTransferException( download.getArtifact(), repository, e ) );
                    }
                }
            }
        }
        if ( metadataDownloads != null )
        {
            for ( final MetadataDownload download : metadataDownloads )
            {
                File remoteFile = new File( basedir, path( download.getMetadata() ) );
                try
                {
                    FileUtils.copyFile( remoteFile, download.getFile() );
                }
                catch ( IOException e )
                {
                    if ( !remoteFile.exists() )
                    {
                        download.setException( new MetadataNotFoundException( download.getMetadata(), repository ) );
                    }
                    else
                    {
                        download.setException( new MetadataTransferException( download.getMetadata(), repository, e ) );
                    }
                }
            }
        }
    }
