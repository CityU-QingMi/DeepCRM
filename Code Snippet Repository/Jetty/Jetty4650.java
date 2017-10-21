        @Override
        public void run()
        {
            String line;
            try (PrintWriter out = new PrintWriter(output))
            {
                while ((line = reader.readLine()) != (null))
                {
                    out.println(line);
                    out.flush();
                }
            }
            catch (IOException ignore)
            {
                /* ignore */
            }
            finally
            {
                IO.close(reader);
                latch.countDown();
            }
        }
