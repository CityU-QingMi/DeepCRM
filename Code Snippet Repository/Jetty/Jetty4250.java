        @Override
        public void event(String name, String data) throws IOException
        {
            synchronized (this)
            {
                output.write(EVENT_FIELD);
                output.write(name.getBytes(StandardCharsets.UTF_8));
                output.write(CRLF);
                data(data);
            }
        }
