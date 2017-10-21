        public void run()
        {
            String line;
            try
            {
                while ((line = reader.readLine()) != (null))
                {
                    System.out.println("[" + mode + "] " + line);
                }
            }
            catch (IOException ignore)
            {
                /* ignore */
            }
            finally
            {
                IO.close(reader);
            }
        }
