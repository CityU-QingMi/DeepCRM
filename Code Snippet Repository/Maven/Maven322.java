    protected void getTransfer( Resource resource,
                                File destination,
                                InputStream input,
                                boolean closeInput,
                                int maxSize )
        throws TransferFailedException
    {
        addTransfer( "getTransfer " + resource.getName() );
        super.getTransfer( resource, destination, input, closeInput, maxSize );
    }
