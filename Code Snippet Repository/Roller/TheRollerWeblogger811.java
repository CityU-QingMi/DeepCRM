    private void extractByParsingRss(String rssLink, String requestURL)
            throws FeedException, IOException {
        SyndFeedInput feedInput = new SyndFeedInput();       
        SyndFeed feed = feedInput.build(
            new InputStreamReader(new URL(rssLink).openStream()));
        String feedTitle = feed.getTitle();

        int count = 0;

        if (mLogger.isDebugEnabled()) {
            mLogger.debug("Feed parsed, title: " + feedTitle);
        }

        for (Object objItem : feed.getEntries()) {
            count++;
            SyndEntry item = (SyndEntry) objItem;
            if (item.getDescription().getValue().contains(requestURL)) {
                mFound = true;
                mPermalink = item.getLink();
                if (feedTitle != null && feedTitle.trim().length() > 0) {
                    mTitle = feedTitle + ": " + item.getTitle();
                } else {
                    mTitle = item.getTitle();
                }
                mExcerpt = item.getDescription().getValue();
                mExcerpt = Utilities.removeHTML(mExcerpt);
                if (mExcerpt.length() > MAX_EXCERPT_CHARS) {
                    mExcerpt = mExcerpt.substring(0, MAX_EXCERPT_CHARS) + "...";
                }
                break;
            }
        }

        if (mLogger.isDebugEnabled()) {
            mLogger.debug("Parsed " + count + " articles, found linkback=" + mFound);
        }
    }
