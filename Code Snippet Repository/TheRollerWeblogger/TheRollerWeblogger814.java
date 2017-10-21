        public void handleText(char[] data, int pos)
        {
            if (mCurrentTag != null && mCurrentTag.equals(Tag.TITLE))
            {
                String newText = new String(data);
                if (mTitle.length() < DESIRED_TITLE_LENGTH)
                {
                    mTitle += newText;
                }
            }
        }
