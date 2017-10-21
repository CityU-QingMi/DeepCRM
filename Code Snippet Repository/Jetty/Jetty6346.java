        @OnMessage
        public void onMessage(InputStream stream, @PathParam("filename") String filename) throws IOException
        {
            File outputFile = new File(outputDir,filename);
            CloseCode closeCode = CloseCodes.NORMAL_CLOSURE;
            String closeReason = "";
            try (FileOutputStream out = new FileOutputStream(outputFile))
            {
                IO.copy(stream,out);
                if (outputFile.exists())
                {
                    closeReason = String.format("Received %,d bytes",outputFile.length());
                    if (LOG.isDebugEnabled())
                        LOG.debug(closeReason);
                }
                else
                {
                    LOG.warn("Uploaded file does not exist: " + outputFile);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace(System.err);
                closeReason = "Error writing file";
                closeCode = CloseCodes.UNEXPECTED_CONDITION;
            }
            finally
            {
                session.close(new CloseReason(closeCode,closeReason));
            }
        }
