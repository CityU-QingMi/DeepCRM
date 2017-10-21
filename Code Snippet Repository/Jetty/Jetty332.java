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
            someToSuccess(exchange);
        }
