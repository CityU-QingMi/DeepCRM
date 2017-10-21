            @Override
            public void onWritePossible() throws IOException
            {
                if (!written)
                {
                    written=true;
                    response.setContentLength(5);
                    servletOutputStream.write("data\n".getBytes());
                }
               
                if (servletOutputStream.isReady())
                {
                    asyncContext.complete();
                }
            }
