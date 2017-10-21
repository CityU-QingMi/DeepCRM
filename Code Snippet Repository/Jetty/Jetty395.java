            @Override
            public void onContent(Response response, ByteBuffer content, Callback callback)
            {
                try
                {
                    onContent(response, content);
                    callback.succeeded();
                }
                catch (Throwable x)
                {
                    callback.failed(x);
                }
            }
