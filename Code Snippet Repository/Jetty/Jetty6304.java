    public int getMessageCount()
    {
        int messageCount = 0;
        for (Object msg : messages)
        {
            if (msg instanceof PartialText)
            {
                PartialText pt = (PartialText)msg;
                if (pt.fin)
                {
                    messageCount++;
                }
            }
            else if (msg instanceof PartialBinary)
            {
                PartialBinary pb = (PartialBinary)msg;
                if (pb.fin)
                {
                    messageCount++;
                }
            }
            else
            {
                messageCount++;
            }
        }
        
        return messageCount;
    }
