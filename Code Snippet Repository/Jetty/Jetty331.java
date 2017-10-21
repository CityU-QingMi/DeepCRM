        @Override
        public void succeeded()
        {
            HttpExchange exchange = getHttpExchange();
            if (exchange == null)
                return;
            HttpContent content = HttpSender.this.content;
            if (content == null)
                return;
            content.succeeded();
            ByteBuffer buffer = content.getContent();
            someToContent(exchange, buffer);
            super.succeeded();
        }
