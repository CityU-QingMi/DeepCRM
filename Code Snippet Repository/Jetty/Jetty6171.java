        public void writeQuotes(String filename) throws IOException
        {
            // read file
            File qfile = MavenTestingUtils.getTestResourceFile(filename);
            List<String> lines = new ArrayList<>();
            try (FileReader reader = new FileReader(qfile); BufferedReader buf = new BufferedReader(reader))
            {
                String line;
                while ((line = buf.readLine()) != null)
                {
                    lines.add(line);
                }
            }
            // write file out, each line on a separate frame, but as
            // 1 whole message
            for (int i = 0; i < lines.size(); i++)
            {
                WebSocketFrame frame;
                if (i == 0)
                {
                    frame = new TextFrame();
                }
                else
                {
                    frame = new ContinuationFrame();
                }
                frame.setFin((i >= (lines.size() - 1)));
                frame.setPayload(BufferUtil.toBuffer(lines.get(i) + "\n"));
                sconnection.write(frame);
            }
        }
