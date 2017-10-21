        public void run()
        {
            String line;
            //System.out.printf("ConsoleStreamer/%s initiated%n",mode);
            try
            {
                while ((line = reader.readLine()) != (null))
                {
                    if (parser != null)
                    {
                        parser.parse(line);
                    }
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
            //System.out.printf("ConsoleStreamer/%s finished%n",mode);
        }
