    protected boolean isGzippedContent(String path)
    {
        if (path == null || _gzipEquivalentFileExtensions==null)
            return false;

        for (String suffix:_gzipEquivalentFileExtensions)
            if (path.endsWith(suffix))
                return true;
        return false;
    }
