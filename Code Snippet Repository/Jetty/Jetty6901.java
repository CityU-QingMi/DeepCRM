        @Override
        public String toString()
        {
            StringBuilder str = new StringBuilder();
            str.append("FrameReadingThread[");
            str.append(",frames=" + frames.size());
            str.append(",errors=" + errors.size());
            str.append(String.format(",totalBytes=%,d",totalBytes));
            str.append(String.format(",totalReadOps=%,d",totalReadOps));
            str.append(String.format(",totalParseOps=%,d",totalParseOps));
            str.append("]");
            return str.toString();
        }
