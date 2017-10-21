        @OnMessage
        public void echo(Session session, Reader input) throws IOException
        {
            char[] buffer = new char[128];
            try (Writer output = session.getBasicRemote().getSendWriter())
            {
                int read;
                while ((read = input.read(buffer)) >= 0)
                    output.write(buffer, 0, read);
            }
        }
