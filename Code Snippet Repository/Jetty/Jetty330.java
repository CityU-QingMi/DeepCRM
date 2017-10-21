        @Override
        public void succeeded()
        {
            try
            {
                HttpContent content = HttpSender.this.content;
                if (content == null)
                    return;
                content.succeeded();
                process();
            }
            catch (Throwable x)
            {
                anyToFailure(x);
            }
        }
