        public void handleStartTag(Tag tag, MutableAttributeSet atts, int pos)
        {
            if (mList.contains(tag) && !mFound)
            {
                mStart = pos;
            }
            else if (mList.contains(tag) && mFound && mEnd == 0)
            {
                mEnd = pos;
            }
            else if (tag.equals(Tag.A))
            {
                String href = (String) atts.getAttribute(HTML.Attribute.HREF);
                if (href == null) {
                    return;
                }
                int hashPos = href.lastIndexOf('#');
                if (hashPos != -1)
                {
                    href = href.substring(0, hashPos);
                }
                if (href != null
                        && (href.equals(mRequestURL) || href
                                .equals(mRequestURLWWW)))
                {
                    mFound = true;
                }
            }
            mCurrentTag = tag;
        }
