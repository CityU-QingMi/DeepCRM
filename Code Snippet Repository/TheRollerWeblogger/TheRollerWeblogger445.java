    public List<WeblogEntryCommentWrapper> getRecentComments(int length) {
        List<WeblogEntryComment> unwrapped = this.pojo.getRecentComments(length);
        List<WeblogEntryCommentWrapper> wrapped = new ArrayList<WeblogEntryCommentWrapper>(unwrapped.size());

        int i = 0;
        for (WeblogEntryComment wec : unwrapped) {
            wrapped.add(i, WeblogEntryCommentWrapper.wrap(wec, urlStrategy));
            i++;
        }
        return wrapped;
    }
