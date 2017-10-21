        @OnMessage
        public void echo(Session session, InputStream input) throws IOException
        {
            byte[] buffer = new byte[128];
            try (OutputStream output = session.getBasicRemote().getSendStream())
            {
                int read;
                while ((read = input.read(buffer)) >= 0)
                    output.write(buffer, 0, read);
            }
        }
