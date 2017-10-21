    @Override
    public void fillInputData( InputData inputData )
        throws TransferFailedException, ResourceDoesNotExistException, AuthorizationException
    {
        Resource resource = inputData.getResource();

        String content = expectedContent.get( resource.getName() );

        if ( content != null )
        {
            resource.setContentLength( content.length() );
            resource.setLastModified( System.currentTimeMillis() );

            try
            {
                inputData.setInputStream( new ByteArrayInputStream( content.getBytes( "UTF-8" ) ) );
            }
            catch ( UnsupportedEncodingException e )
            {
                throw new Error( "broken JVM", e );
            }
        }
        else
        {
            throw new ResourceDoesNotExistException( "No content provided for " + resource.getName() );
        }
    }
