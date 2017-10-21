    private HttpContent load(String pathInContext, Resource resource, int maxBufferSize)
        throws IOException
    {   
        if (resource==null || !resource.exists())
            return null;
        
        if (resource.isDirectory())
            return new ResourceHttpContent(resource,_mimeTypes.getMimeByExtension(resource.toString()),maxBufferSize);

        // Look for a precompressed resource or content
        String mt = _mimeTypes.getMimeByExtension(pathInContext);
        if (_precompressedFormats.length > 0)
        {
            // Is there a compressed resource?
            Map<CompressedContentFormat, HttpContent> compressedContents = new HashMap<>(_precompressedFormats.length);
            for (CompressedContentFormat format : _precompressedFormats)
            {
                String compressedPathInContext = pathInContext + format._extension;
                Resource compressedResource = _factory.getResource(compressedPathInContext);
                if (compressedResource != null && compressedResource.exists() && compressedResource.lastModified() >= resource.lastModified()
                        && compressedResource.length() < resource.length())
                    compressedContents.put(format,
                            new ResourceHttpContent(compressedResource,_mimeTypes.getMimeByExtension(compressedPathInContext),maxBufferSize));
            }
            if (!compressedContents.isEmpty())
                return new ResourceHttpContent(resource,mt,maxBufferSize,compressedContents);
        }
        return new ResourceHttpContent(resource,mt,maxBufferSize);
    }
