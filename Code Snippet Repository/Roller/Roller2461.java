        public void handleEndTag(Tag tag, int pos)
        {
            if (mList.contains(tag) && mFound && mEnd == 0)
            {
                mEnd = pos;
            }
            else if (mList.contains(tag) && !mFound)
            {
                mStart = pos;
            }
            else
            {
                mCurrentTag = null;
            }
        }
