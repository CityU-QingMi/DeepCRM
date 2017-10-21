        @OnMessage
        public synchronized void onMessage(Quotes msg)
        {
            Integer h=hashCode();
            messageQueue.add(msg);
            System.out.printf("%x: Quotes from: %s%n",h,msg.author);
            for (String quote : msg.quotes)
            {
                System.out.printf("%x: - %s%n",h,quote);
            }
        }
