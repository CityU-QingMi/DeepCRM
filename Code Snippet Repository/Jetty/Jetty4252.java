        @Override
        public void comment(String comment) throws IOException
        {
            synchronized (this)
            {
                output.write(COMMENT_FIELD);
                output.write(comment.getBytes(StandardCharsets.UTF_8));
                output.write(CRLF);
                output.write(CRLF);
                flush();
            }
        }
