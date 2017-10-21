        private void deflate(FrameEntry entry)
        {
            Frame frame = entry.frame;
            BatchMode batchMode = entry.batchMode;
            if (OpCode.isControlFrame(frame.getOpCode()))
            {
                // Do not deflate control frames
                nextOutgoingFrame(frame,this,batchMode);
                return;
            }
            
            compress(entry,true);
        }
