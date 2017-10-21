    public MediaFile getMediaFileByOriginalPath(Weblog weblog, String origpath)
            throws WebloggerException {

        if (null == origpath) {
            return null;
        }

        if (!origpath.startsWith("/")) {
            origpath = "/" + origpath;
        }

        TypedQuery<MediaFile> q = this.strategy
                .getNamedQuery("MediaFile.getByWeblogAndOrigpath", MediaFile.class);
        q.setParameter(1, weblog);
        q.setParameter(2, origpath);
        MediaFile mf;
        try {
            mf = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        FileContentManager cmgr = WebloggerFactory.getWeblogger()
                .getFileContentManager();
        FileContent content = cmgr.getFileContent(
                mf.getDirectory().getWeblog(), mf.getId());
        mf.setContent(content);
        return mf;
    }
