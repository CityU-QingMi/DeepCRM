    public ReloadingClassLoader(final ClassLoader pParent) {
        super(pParent);
        parent = pParent;
        URL parentRoot = pParent.getResource("");
        FileManager fileManager = ActionContext.getContext().getInstance(FileManagerFactory.class).getFileManager();
        URL root = fileManager.normalizeToFileProtocol(parentRoot);
        root = ObjectUtils.defaultIfNull(root, parentRoot);
        try {
            if (root != null) {
                stores = new ResourceStore[]{new FileResourceStore(new File(root.toURI()))};
            } else {
                throw new XWorkException("Unable to start the reloadable class loader, consider setting 'struts.convention.classes.reload' to false");
            }
        } catch (URISyntaxException e) {
            throw new XWorkException("Unable to start the reloadable class loader, consider setting 'struts.convention.classes.reload' to false", e);
        } catch (RuntimeException e) {
            // see WW-3121
            // TODO: Fix this for a reloading mechanism to be marked as stable
            if (root != null) {
                LOG.error("Exception while trying to build the ResourceStore for URL [{}]", root.toString(), e);
            }
            else {
                LOG.error("Exception while trying to get root resource from class loader", e);
            }
            LOG.error("Consider setting struts.convention.classes.reload=false");
            throw e;
        }

        delegate = new ResourceStoreClassLoader(parent, stores);
    }
