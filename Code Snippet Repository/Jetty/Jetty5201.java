    private PathResource(PathResource parent, String childPath) throws MalformedURLException
    {
        // Calculate the URI and the path separately, so that any aliasing done by
        // FileSystem.getPath(path,childPath) is visiable as a difference to the URI
        // obtained via URIUtil.addDecodedPath(uri,childPath)

        this.path = parent.path.getFileSystem().getPath(parent.path.toString(), childPath);
        if (isDirectory() &&!childPath.endsWith("/"))
            childPath+="/";
        this.uri = URIUtil.addPath(parent.uri,childPath);
        this.alias = checkAliasPath();
    }
