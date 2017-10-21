        @Override
        public void resolve(String host, int port, Promise<List<InetSocketAddress>> promise)
        {
            try
            {
                InetAddress[] addresses = InetAddress.getAllByName(host);

                List<InetSocketAddress> result = new ArrayList<>(addresses.length);
                for (InetAddress address : addresses)
                    result.add(new InetSocketAddress(address, port));

                if (result.isEmpty())
                    promise.failed(new UnknownHostException());
                else
                    promise.succeeded(result);
            }
            catch (Throwable x)
            {
                promise.failed(x);
            }
        }
