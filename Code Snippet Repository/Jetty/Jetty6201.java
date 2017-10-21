        @Override
        public void onMessage(ByteBuffer messagePart, boolean last) {
            final ByteBuffer bufferCopy = ByteBuffer.allocate(messagePart.capacity());
            bufferCopy.put(messagePart);
            currentMessage.add(bufferCopy);
            if (last) {
                int totalSize = 0;
                for (ByteBuffer bb : currentMessage) {
                    totalSize += bb.capacity();
                }
                final ByteBuffer result = ByteBuffer.allocate(totalSize);
                for (ByteBuffer bb : currentMessage) {
                    result.put(bb);
                }
                final String stringResult = new String(result.array());
                getMessageQueue().add(stringResult);
                currentMessage.clear();
            }

        }
