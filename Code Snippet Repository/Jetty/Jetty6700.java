        public ByteBuffer generateWindowed(Frame... frames)
        {
            // Create Buffer to hold all generated frames in a single buffer
            int completeBufSize = 0;
            for (Frame f : frames)
            {
                completeBufSize += Generator.MAX_HEADER_LENGTH + f.getPayloadLength();
            }

            ByteBuffer completeBuf = ByteBuffer.allocate(completeBufSize);
            BufferUtil.clearToFill(completeBuf);

            // Generate from all frames
            Generator generator = new UnitGenerator();

            for (Frame f : frames)
            {
                ByteBuffer header = generator.generateHeaderBytes(f);
                totalBytes += BufferUtil.put(header,completeBuf);

                if (f.hasPayload())
                {
                    ByteBuffer payload=f.getPayload();
                    totalBytes += payload.remaining();
                    totalParts++;
                    completeBuf.put(payload.slice());
                }
            }

            // Return results
            BufferUtil.flipToFlush(completeBuf,0);
            return completeBuf;
        }
